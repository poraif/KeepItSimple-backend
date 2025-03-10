package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.dto.responsebody.term.TermAndCurrentVersion;
import ie.por.keepitsimple.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {


    @Query("""
    select 
    term.name as term_name,
    term.category as category,
    termVersion.shortDef as shortDef,
    termVersion.longDef as longDef,
    termVersion.codeSnippet as codeSnippet,
    termVersion.exampleUsage as exampleUsage
    from Term term
    join term.currentVersion termVersion
    where termVersion.approved = true
    and term.name = :name
    """)
    public TermAndCurrentVersion getTermAndCurrentVersionByName(String name);

    public Term findTermByName(String name);

}