package elasticSearchEcom.RepositoryElasticSearch;

import java.util.List;

import elasticSearchEcom.model.User;

public interface ElasticSearchRepo  {

	List<User> getAllUsers();
	User getUserById (String userId);
	User addNewUser(User user);
	//Object getAllUserSetting(String userId);

	
}
