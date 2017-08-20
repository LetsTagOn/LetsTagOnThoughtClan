package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityInvite;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Interface OpportunityInviteRepository.
 */
public interface OpportunityInviteRepository extends PagingAndSortingRepository<OpportunityInvite, Long> {

	/**
	 * Find all by invited party.
	 *
	 * @param invitedParty the invited party
	 * @param page the page
	 * @return the page
	 */
	Page<OpportunityInvite> findAllByInvitedParty(@Param("invitedParty") Party invitedParty, Pageable page);

	/**
	 * Find all by invited party and accepted.
	 *
	 * @param invitedParty the invited party
	 * @param accepted the accepted
	 * @param page the page
	 * @return the page
	 */
	Page<OpportunityInvite> findAllByInvitedPartyAndAccepted(@Param("invitedParty") Party invitedParty,
			@Param("accepted") Boolean accepted, Pageable page);

	/**
	 * Find all by invited by party.
	 *
	 * @param invitedByParty the invited by party
	 * @param page the page
	 * @return the page
	 */
	Page<OpportunityInvite> findAllByInvitedByParty(@Param("invitedByParty") Party invitedByParty, Pageable page);

	/**
	 * Find one by invited by party and invited party and opportunity bean.
	 *
	 * @param invitedByParty the invited by party
	 * @param invitedParty the invited party
	 * @param opportunityBean the opportunity bean
	 * @return the opportunity invite
	 */
	OpportunityInvite findOneByInvitedByPartyAndInvitedPartyAndOpportunityBean(
			@Param("invitedByParty") Party invitedByParty, @Param("invitedParty") Party invitedParty,
			@Param("opportunityBean") Opportunity opportunityBean);
	
	/**
	 * Find all by opportunity bean and invited party list.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param invitedParty the invited party
	 * @param page the page
	 * @return the page
	 */
	@Query("Select i from OpportunityInvite i where i.opportunityBean = :opportunityBean and i.invitedParty IN :invitedParty")
	Page<OpportunityInvite> findAllByOpportunityBeanAndInvitedPartyList(@Param("opportunityBean") Opportunity opportunityBean,@Param("invitedParty") List<Party> invitedParty, Pageable page);
}
