package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.dto.responsebody.term.TermAndCurrentVersion;
import ie.por.keepitsimple.model.TermVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TermVersionRepository extends JpaRepository<TermVersion, Long> {


    TermVersion getTermVersionById(Long id);

    @Query("""
    select termversion from TermVersion termversion
    where termversion.approved = true
    and termversion.term.name = :name
       order by (select sum(v.voteValue) from Vote v where v.termVersion = termversion) desc
           limit 1
    """)
    Optional<TermVersion> findTermVersionByTermAndHighestVote(@Param("name")String name);


@Query("""
    select 
    account.username as username,
    term.name as name,
    term.category as category,
    termVersion.shortDef as shortDef,
    termVersion.longDef as longDef,
    termVersion.codeSnippet as codeSnippet,
    termVersion.exampleUsage as exampleUsage,
    termVersion.id as versionId
    from Term term
    join term.currentVersion termVersion
    join termVersion.account account
    where termVersion.approved = true
    and term.name = :name
    """)
TermAndCurrentVersion getTermAndCurrentVersionByName(@Param("name")String name);

}
