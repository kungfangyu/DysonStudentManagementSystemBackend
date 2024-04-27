package com.dysonstudentmanagement.dsm.idgenerator;

import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import jakarta.persistence.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class ProgrammeIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        Programme programme = (Programme) o;
        String name;
        if (programme.getName() == null) {
            name = generateRandomString(true, 11); // Generate a random string of length 11
        } else {
            name = programme.getName();
        }

        // Ensure the length of the name does not exceed 11 characters
        if (name.length() > 11) {
            name = name.substring(0, 11);
        }

        // Generate the ProgrammeID using the modified name
        String[] nameWords = name.split(" ", 4);

        int nonRandomContribution = 8;
        float wordContributionToId = 8 / nameWords.length;

        Session session = (Session) sharedSessionContractImplementor.getSession();
        String proposedId = "";
        boolean idExists = true;

        for (int i = 0; i < 10; i++) {
            for (String word : nameWords) {
                int substringLength = Math.min(word.length(), 4); // Ensure substring length does not exceed word length
                proposedId = proposedId + word.substring(0, substringLength);

            }

            idExists = session.createQuery("SELECT COUNT(p) FROM Programme p WHERE p.programmeID = :id", Long.class)
                    .setParameter("id", proposedId)
                    .setMaxResults(1)
                    .getSingleResult() > 0;

            if (!idExists) {
                // Return the generated ID as a String
                return proposedId;
            } else {
                proposedId = "";
            }
        }
        throw new NonUniqueResultException("Could not generate unique ProgrammeID.");
    }

    private String generateRandomString(boolean alphabetic, int targetStringLength) {
        int leftLimit;
        int rightLimit;
        Random random = new Random();
        StringBuilder generatedString = new StringBuilder();
        if (alphabetic) {
            leftLimit = 97;
            rightLimit = 122;
        } else {
            leftLimit = 48;
            rightLimit = 122;
        }
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            generatedString.append((char) randomLimitedInt);
        }
        return generatedString.toString();
    }
}
