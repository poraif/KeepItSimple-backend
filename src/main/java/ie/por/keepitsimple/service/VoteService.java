package ie.por.keepitsimple.service;

import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.model.Vote;
import ie.por.keepitsimple.model.VoteId;
import ie.por.keepitsimple.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    AccountService accountService;

    @Autowired
    TermVersionService termVersionService;

    @Autowired
    private VoteRepository voteRepository;

    private VoteId voteId;

    public void addVote(Long id, String username, Integer voteValue) {
        if (voteValue != null && voteValue == -1 | voteValue == 1) {
            Account account = accountService.findAccountByUsername(username);
            voteId = new VoteId(id, account.getId());
            Optional<Vote> voteToUpdate = voteRepository.findById(voteId);
            if (voteToUpdate.isPresent()) {
                voteToUpdate.get().setVoteValue(voteValue);
                voteRepository.save(voteToUpdate.get());
            } else {
                Vote vote = new Vote();
                TermVersion termVersion = termVersionService.findTermVersionById(id);
                vote.setTermVersion(termVersion);
                vote.setVoteValue(voteValue);
                vote.setAccount(account);
                voteRepository.save(vote);
            }
        } else {
            throw new IllegalArgumentException("Vote value must be -1 or -1");
        }
    }
}

