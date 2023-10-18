package dataBaseManipulations.skillsManipulations;


import dataBaseManipulations.BaseSetup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class DeleteAllAddedSkills extends BaseSetup {

    public static void deleteAllAddedSkills() throws SQLException {
        List<Integer> skills_id = new ArrayList<>();
        List<Integer> exeprtiseProfilesIdsInSKills = new ArrayList<>();

        String maxExistingExpertiseId = "SELECT * FROM skills";


        rs = statement.executeQuery(maxExistingExpertiseId);
        while (rs.next()) {
            int id = rs.getInt("skill_id");
            skills_id.add(id);
        }
        System.out.println(skills_id.size());
        String sqlSelectIdFromExpertiseSkillsTable = "SELECT * FROM expertise_profile_skills";
        rs = statement.executeQuery(sqlSelectIdFromExpertiseSkillsTable);
        while (rs.next()) {
            int id = rs.getInt("skills_skill_id");
            exeprtiseProfilesIdsInSKills.add(id);
        }
        System.out.println(exeprtiseProfilesIdsInSKills.size());
        for (int expertiseId : exeprtiseProfilesIdsInSKills) {
            String deleteExpertiseSkill = format("DELETE FROM expertise_profile_skills WHERE skills_skill_id=%d", expertiseId);
            statement.executeUpdate(deleteExpertiseSkill);
            System.out.println("Expertise was deleted");
        }


        for (int id : skills_id) {
            String deletePostlikes = format("DELETE FROM `skills` WHERE skill_id=%d", id);
            statement.executeUpdate(deletePostlikes);
            System.out.println("Skill was deleted");
        }

        if (skills_id.size() == 0) {
            System.out.println("There are no existing added skills in the system.");
        }
    }
}
