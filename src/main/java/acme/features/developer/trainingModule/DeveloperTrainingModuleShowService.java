
package acme.features.developer.trainingModule;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.trainingModule.Difficulty;
import acme.entities.trainingModule.TrainingModule;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleShowService extends AbstractService<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingModuleRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Developer developer;
		int developerRequestId;
		int trainingModuleId;
		TrainingModule trainingModule;

		trainingModuleId = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findTrainingModuleById(trainingModuleId);
		developer = trainingModule == null ? null : trainingModule.getDeveloper();
		developerRequestId = super.getRequest().getPrincipal().getActiveRoleId();
		if (developer != null)
			status = super.getRequest().getPrincipal().hasRole(developer) && developer.getId() == developerRequestId;
		else
			status = false;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		TrainingModule object;
		int trainingModuleId;

		trainingModuleId = super.getRequest().getData("id", int.class);
		object = this.repository.findTrainingModuleById(trainingModuleId);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		SelectChoices choices;
		SelectChoices projectChoices = new SelectChoices();
		Collection<Project> projects;
		projects = this.repository.findPublishedProjects();
		Dataset dataset;

		for (final Project c : projects)
			if (object.getProject() != null && object.getProject().getId() == c.getId())
				projectChoices.add(Integer.toString(c.getId()), "Code: " + c.getCode() + " - " + "Title: " + c.getTitle(), true);
			else
				projectChoices.add(Integer.toString(c.getId()), "Code: " + c.getCode() + " - " + "Title: " + c.getTitle(), false);

		choices = SelectChoices.from(Difficulty.class, object.getDifficultyLevel());

		dataset = super.unbind(object, "code", "creationMoment", "details", "updateMoment", "link", "totalTime", "published");
		dataset.put("developer", object.getDeveloper().getUserAccount().getUsername());
		dataset.put("difficultyLevel", choices.getSelected().getKey());
		dataset.put("difficulties", choices);
		dataset.put("projects", projectChoices);

		super.getResponse().addData(dataset);
	}

}
