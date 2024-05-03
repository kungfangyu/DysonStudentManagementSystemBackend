package com.dysonstudentmanagement.dsm.idgenerator;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import jakarta.persistence.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

/*
ModuleIDGenerator

A custom Id generator used by the ModuleDetails entity

Original Author: Billy Peters 23/04/2024
 */
public class ModuleIDGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        ModuleDetails module = (ModuleDetails) o;
        String name;
        if (module.getModuleName() == null){
            name = generateRandomString(true,50);
        }else{
            name = module.getModuleName();
        }
        String[] nameWords = name.split(" ",4);

        int nonRandomContribution = 8;
        float wordContributionToId = 8.0f/nameWords.length;

        Session session = (Session) sharedSessionContractImplementor.getSession();
        StringBuilder firstIdComponent = new StringBuilder();

        for(String word:nameWords) {
            int contribution = Math.min((int) Math.ceil(wordContributionToId), word.length());
            firstIdComponent.append(word.substring(0, contribution));
            if (firstIdComponent.length() >= nonRandomContribution) {
                break;
            }
        }
        String proposedId;
        boolean idExists;

        for(int i = 0; i < 1000; i++){

            proposedId = firstIdComponent + generateRandomString(true,4);
            idExists = session.createQuery("SELECT COUNT(u) FROM ModuleDetails u WHERE u.moduleID = :id", Long.class).setParameter("id", proposedId).setMaxResults(1).getSingleResult() > 0;

            if (!idExists) {
                return proposedId;
            }
        }
        throw new NonUniqueResultException("Could not generate unique ID for user");
    }

    private String generateRandomString(boolean alphabetic, int targetStringLength){
        int leftLimit;
        int rightLimit;
        Random random = new Random();
        String generatedString;
        if(alphabetic){
            leftLimit = 97;
            rightLimit = 122;
        } else{
            leftLimit = 48;
            rightLimit = 122;
        }
        generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return(generatedString);
    }
}
