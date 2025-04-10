package ie.por.keepitsimple.service;

import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.model.Vote;
import ie.por.keepitsimple.model.VoteId;
import ie.por.keepitsimple.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addVote(Long id, String username, Integer voteValue) {
        if (voteValue != null && voteValue == -1 | voteValue == 1 | voteValue == 0) {
            Account account = accountService.findAccountByUsername(username);
            TermVersion termVersion = termVersionService.findTermVersionById(id);
            VoteId voteId = new VoteId(account.getId(), termVersion.getId());
            Optional<Vote> voteToUpdate = voteRepository.findById(voteId);
            if (voteToUpdate.isPresent()) {
                voteToUpdate.get().setVoteValue(voteValue);
                voteRepository.save(voteToUpdate.get());
            } else {
                Vote vote = new Vote();
                vote.setTermVersion(termVersion);
                vote.setVoteValue(voteValue);
                vote.setAccount(account);
                vote.setId(voteId);
                voteRepository.save(vote);
            }
        } else {
            throw new IllegalArgumentException("Vote value must be -1 or -1");
        }
    }
}

