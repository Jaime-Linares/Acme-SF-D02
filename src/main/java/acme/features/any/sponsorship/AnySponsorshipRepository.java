
package acme.features.any.sponsorship;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface AnySponsorshipRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.published = true")
	Collection<Sponsorship> findAllSponsorshipPublished();

	@Query("select s from Sponsorship s where s.id = :sponsorshipId")
	Optional<Sponsorship> findSponsorshipById(int sponsorshipId);

}
