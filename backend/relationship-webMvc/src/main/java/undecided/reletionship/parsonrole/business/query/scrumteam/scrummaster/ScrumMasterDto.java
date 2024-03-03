package undecided.reletionship.parsonrole.business.query.scrumteam.scrummaster;

import undecided.reletionship.parsonrole.model.scrumteam.scrummaster.ScrumMaster;
import undecided.reletionship.party.model.person.Person;

import java.time.LocalDate;

public record ScrumMasterDto(Long employeeId, String employeeNo, String firstName, String lastName,
                             LocalDate dateOfBirth) {
    public static ScrumMasterDto reconstruct(ScrumMaster scrumMaster, Person person) {
        return new ScrumMasterDto(scrumMaster.getId(), scrumMaster.getEmployeeNo(),
                person.getFirstName(), person.getLastName(), person.getDateOfBirth());

    }

    public ScrumMaster convertToEntity() {
        return ScrumMaster.createAtInsert(employeeId, employeeNo);
        
    }
}
