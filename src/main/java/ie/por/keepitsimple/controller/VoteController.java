package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.model.Vote;
import ie.por.keepitsimple.service.AccountService;
import ie.por.keepitsimple.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/term/{name}/termversion/{id}")
public class VoteController {


    @Autowired
    VoteService voteService;

    @PutMapping (value="/vote")
    public void vote(@PathVariable Long id, @RequestBody Integer voteValue) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        voteService.addVote(id, username, voteValue);
    }

}





