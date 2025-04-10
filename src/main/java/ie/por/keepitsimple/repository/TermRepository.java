package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.dto.responsebody.term.TermAndCurrentVersion;
import ie.por.keepitsimple.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {


    @Query("""
    select 
    account.username as username,
    vote.voteValue as loggedInUserVote,
    term.name as name,
    term.category as category,
    termVersion.shortDef as shortDef,
    termVersion.longDef as longDef,
    termVersion.codeSnippet as codeSnippet,
    termVersion.exampleUsage as exampleUsage,
    termVersion.id as id
    from Term term
    join term.currentVersion termVersion
    left join termVersion.account account
    left join Vote vote on vote.termVersion = termVersion and vote.account = account
    where termVersion.approved = true
    and term.name = :name
    """)
    TermAndCurrentVersion getTermAndCurrentVersionByName(@Param("name")String name);

    @Query("""
    select 
    account.username as username,
    vote.voteValue as loggedInUserVote,
    term.name as name,
    term.category as category,
    termVersion.shortDef as shortDef,
    termVersion.longDef as longDef,
    termVersion.codeSnippet as codeSnippet,
    termVersion.exampleUsage as exampleUsage,
    termVersion.id as id
    from Term term
    join term.currentVersion termVersion
    left join termVersion.account account
    left join Vote vote on vote.termVersion = termVersion and vote.account = account
    where termVersion.approved = true
    and term.name = :name
    """)
    public List<TermAndCurrentVersion> getUnapprovedVersions();

    public Term findTermByName(String name);

    @Query("""
    select t.name from Term t order by t.name desc
    """)
    List<String> findAllTermNames();

}