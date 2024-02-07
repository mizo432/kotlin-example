package undecided.reletionship.model.parsonrole.projectadmin;

import org.junit.jupiter.api.Test;
import undecided.reletionship.parsonrole.model.projectadmin.ProjectAdministrator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectAdministratorTest {
    /**
     * Method under test: {@link ProjectAdministrator#equals(Object)}
     */
    @Test
    void equals() {
        // Arrange
        ProjectAdministrator projectAdministrator = new ProjectAdministrator();
        projectAdministrator.setProjectAdministratorId(1L);

        // Act and Assert
        assertNotEquals(projectAdministrator, null);
    }

    /**
     * Method under test: {@link ProjectAdministrator#equals(Object)}
     */
    @Test
    void equals2() {
        // Arrange
        ProjectAdministrator projectAdministrator = new ProjectAdministrator();
        projectAdministrator.setProjectAdministratorId(1L);

        // Act and Assert
        assertNotEquals(projectAdministrator, "Different type to ProjectAdministrator");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectAdministrator#equals(Object)}
     *   <li>{@link ProjectAdministrator#hashCode()}
     * </ul>
     */
    @Test
    void equals3() {
        // Arrange
        ProjectAdministrator projectAdministrator = new ProjectAdministrator();
        projectAdministrator.setProjectAdministratorId(1L);

        // Act and Assert
        assertEquals(projectAdministrator, projectAdministrator);
        int expectedHashCodeResult = projectAdministrator.hashCode();
        assertEquals(expectedHashCodeResult, projectAdministrator.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectAdministrator#equals(Object)}
     *   <li>{@link ProjectAdministrator#hashCode()}
     * </ul>
     */
    @Test
    void equals4() {
        // Arrange
        ProjectAdministrator projectAdministrator = new ProjectAdministrator();
        projectAdministrator.setProjectAdministratorId(1L);

        ProjectAdministrator projectAdministrator2 = new ProjectAdministrator();
        projectAdministrator2.setProjectAdministratorId(1L);

        // Act and Assert
        assertEquals(projectAdministrator, projectAdministrator2);
        int expectedHashCodeResult = projectAdministrator.hashCode();
        assertEquals(expectedHashCodeResult, projectAdministrator2.hashCode());
    }

    /**
     * Method under test: {@link ProjectAdministrator#equals(Object)}
     */
    @Test
    void equals5() {
        // Arrange
        ProjectAdministrator projectAdministrator = new ProjectAdministrator();
        projectAdministrator.setProjectAdministratorId(2L);

        ProjectAdministrator projectAdministrator2 = new ProjectAdministrator();
        projectAdministrator2.setProjectAdministratorId(1L);

        // Act and Assert
        assertNotEquals(projectAdministrator, projectAdministrator2);
    }

    /**
     * Method under test: {@link ProjectAdministrator#equals(Object)}
     */
    @Test
    void equals6() {
        // Arrange
        ProjectAdministrator projectAdministrator = new ProjectAdministrator();
        projectAdministrator.setProjectAdministratorId(null);

        ProjectAdministrator projectAdministrator2 = new ProjectAdministrator();
        projectAdministrator2.setProjectAdministratorId(1L);

        // Act and Assert
        assertNotEquals(projectAdministrator, projectAdministrator2);
    }
}
