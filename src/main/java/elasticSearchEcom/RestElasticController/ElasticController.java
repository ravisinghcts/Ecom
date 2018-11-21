package elasticSearchEcom.RestElasticController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import elasticSearchEcom.RepositoryElasticSearch.ElasticSearchRepo;
import elasticSearchEcom.model.User;

@RestController
@RequestMapping("/elastic")
public class ElasticController {
	
	@Autowired
	
	gggg
	ElasticSearchRepo elasticSearchRepo;
	/*
	@Autowired*/
	RestTemplate restTemplate=new RestTemplate();
	
	@RequestMapping("/all")
	List<User> getAllUsers(){
		List<User> target =new ArrayList<>();
		elasticSearchRepo.getAllUsers().forEach(target::add);
		return target;
	}
	
	@RequestMapping("/id/{userId}")
	User getUserById (@PathVariable("userId") String userId) {
		
		User user=elasticSearchRepo.getUserById(userId);
		return user;
	}
	
/*	@RequestMapping(value="/new" ,method=RequestMethod.POST)
	User addNewUser(@RequestBody User user) {
		elasticSearchRepo.addNewUser(user);
		return user;
	}*/
	
	//For Testing
	@RequestMapping(value="/new" ,method=RequestMethod.POST)
	public User addNewUser(@RequestBody User user) {
		 User user1=restTemplate.postForObject("http://localhost:9200/my_index/user/"+user.getUserId(), user, User.class);
			 return user1;
	}

}
