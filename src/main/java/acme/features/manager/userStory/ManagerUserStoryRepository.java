
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.userStories.UserStory;

@Repository
public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select us from UserStory us where us.id = :userStoryId")
	UserStory findUserStoryById(int userStoryId);

	@Query("select aw.userStory from AssociatedWith aw where aw.project.id = :projectId")
	Collection<UserStory> findManyUserStoriesByProjectId(int projectId);

	@Query("select p from Project p where p.id = :projectId")
	Project findProjectById(int projectId);

}
