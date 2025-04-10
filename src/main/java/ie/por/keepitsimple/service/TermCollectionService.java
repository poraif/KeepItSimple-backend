package ie.por.keepitsimple.service;

import ie.por.keepitsimple.dto.requestbody.term.AddTermReqBody;
import ie.por.keepitsimple.dto.requestbody.termcollection.AddTermCollectionReqBody;
import ie.por.keepitsimple.dto.responsebody.termcollection.TermCollectionResponseBody;
import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.model.TermCollection;
import ie.por.keepitsimple.repository.TermCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TermCollectionService {

    @Autowired
    private TermCollectionRepository termCollectionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TermService termService;

    public void addCollection(String username, AddTermCollectionReqBody requestBody) throws Exception {
        TermCollection termCollection = new TermCollection();
        Account account = accountService.findAccountByUsername(username);
        Optional<TermCollection> existingTermCollection = termCollectionRepository.findByName(requestBody.getName());
        if (account != null && existingTermCollection.isEmpty()) {
            termCollection.setAccount(account);
            termCollection.setName(requestBody.getName());
            termCollection.setDescription(requestBody.getDescription());
            termCollectionRepository.save(termCollection);
        }
        else {
            throw new Exception("account doesnt exist or user already has collection with this name");
        }
    }

    public void updateCollectionDetails(String username, AddTermCollectionReqBody requestBody, String collectionName) throws Exception {
        Account account = accountService.findAccountByUsername(username);
        Optional<TermCollection> existingTermCollection = termCollectionRepository.findByName(collectionName);
        if (existingTermCollection.isPresent() && existingTermCollection.get().getAccount() == account) {
            existingTermCollection.get().setName(requestBody.getName());
            existingTermCollection.get().setDescription(requestBody.getDescription());
            termCollectionRepository.save(existingTermCollection.get());
        }
        else {
            throw new Exception("invalid account or collection wasn't found");
        }
        }


    public void addTermToCollection(String username, String collectionName, String termName) throws Exception {
        Account account = accountService.findAccountByUsername(username);
        Optional<TermCollection> termCollection = termCollectionRepository.findByName(collectionName);
        Set<Term> collectionTerms = termCollection.get().getTerms();
        Term term = termService.findTermByName(termName);
        if (term != null && account != null && termCollection.isPresent()) {
            collectionTerms.add(term);
            termCollection.get().setTerms(collectionTerms);
            termCollectionRepository.save(termCollection.get());
        }
        else {
            throw new Exception("term, collection or user missing");
        }
    }

    public void removeTermFromCollection(String username, String collectionName, String termName) throws Exception {
        Account account = accountService.findAccountByUsername(username);
        Optional<TermCollection> termCollection = termCollectionRepository.findByName(collectionName);
        Set<Term> collectionTerms = termCollection.get().getTerms();
        Term term = termService.findTermByName(termName);
        if (term != null && account != null && termCollection.isPresent()) {
            collectionTerms.remove(term);
            termCollection.get().setTerms(collectionTerms);
            termCollectionRepository.save(termCollection.get());
        }
        else {
            throw new Exception("term, collection or user missing");
        }
    }

    public List<TermCollectionResponseBody> getUserCollections(String username) {
        Account account = accountService.findAccountByUsername(username);
        if (account != null) {
            List<TermCollectionResponseBody> returnedCollections = new ArrayList<>();
            List<TermCollection> collections = account.getTermCollections();
            for (TermCollection collection : collections) {
                TermCollectionResponseBody responseBody =
                        new TermCollectionResponseBody(collection.getName(), collection.getDescription());
                returnedCollections.add(responseBody);
            }
            return returnedCollections;
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteCollection(String username, String collectionName) {
        Account account = accountService.findAccountByUsername(username);
        Optional<TermCollection> termCollection = termCollectionRepository.findByName(collectionName);
        if (account != null && termCollection.isPresent()) {
            termCollectionRepository.delete(termCollection.get());
        }
    }

    public Set<AddTermReqBody> getCollectionTerms(String collectionName) {
        Optional<TermCollection> termCollection = termCollectionRepository.findByName(collectionName);
        Set<AddTermReqBody> returnedTerms = new HashSet<>();
        if (termCollection.isPresent()) {
            Set<Term> terms = termCollection.get().getTerms();
            for (Term term : terms) {
                AddTermReqBody reqBody = new AddTermReqBody(term.getName(), term.getCategory());
                returnedTerms.add(reqBody);
            }
            return returnedTerms;
        }
        else {
            return new HashSet<>();
        }
    }
}

