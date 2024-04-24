package com.dysonstudentmanagement.dsm.idgenerator;

import jakarta.persistence.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

public class ModuleIDGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        Module module = (Module) o;
        String name;
        if (module.getName() == null){
            name = generateRandomString(true,50);
        }else{
            name = module.getName();
        }
        String[] nameWords = name.split(" ",4);

        int nonRandomContribution = 8;
        float wordContributionToId = 8/nameWords.length;

        Session session = (Session) sharedSessionContractImplementor.getSession();
        String proposedId = "";
        boolean idExists = true;

        for(int i = 0; i < 10; i++){
            for(String word:nameWords){
                proposedId = proposedId + word.substring(0,(nonRandomContribution-proposedId.length() < wordContributionToId)?(int)Math.ceil(wordContributionToId): nonRandomContribution-proposedId.length());
            }
            proposedId = proposedId + generateRandomString(true,4);
            idExists = session.createQuery("SELECT COUNT(u) FROM ModuleDetails u WHERE u.moduleID = :id", Long.class).setParameter("id", proposedId).setMaxResults(1).getSingleResult() > 0;

            if (!idExists) {
                return proposedId;
            }else{
                proposedId = "";
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
