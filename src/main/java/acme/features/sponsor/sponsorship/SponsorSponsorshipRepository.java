
package acme.features.sponsor.sponsorship;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface SponsorSponsorshipRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.sponsor.id = :sponsorId")
	Collection<Sponsorship> findSponsorshipBySponsorId(int sponsorId);

	@Query("select s from Sponsorship s where s.id = :sponsorshipId")
	Optional<Sponsorship> findSponsorshipById(int sponsorshipId);

}
