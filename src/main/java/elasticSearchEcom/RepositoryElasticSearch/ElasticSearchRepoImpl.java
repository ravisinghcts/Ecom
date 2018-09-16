package elasticSearchEcom.RepositoryElasticSearch;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import elasticSearchEcom.model.User;

@Repository
public class ElasticSearchRepoImpl implements ElasticSearchRepo {
   
	@Value("${elasticsearch.index.name}")
	private String indexName;
	
	@Value("${elasticsearch.user.type}")
	private String userTypeName;
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
/*	@Autowired
	RestTemplate restTemplate;*/
	
  @Override
	public List<User> getAllUsers() {
		
		SearchQuery getAllQuery =new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
			
		return elasticsearchTemplate.queryForList(getAllQuery, User.class);
	}

	@Override
	public User getUserById(String userId) {

		SearchQuery searchQuery =new NativeSearchQueryBuilder().withFilter(QueryBuilders.matchQuery("userId", userId)).build();
		
		List<User> users =elasticsearchTemplate.queryForList(searchQuery, User.class);
		
		
		//List<String> lst=elasticsearchTemplate.queryForIds(searchQuery);
		
		
		if(!users.isEmpty()) {
			
			return users.get(0);
		}
		
		return null;
	}

	@Override
	public User addNewUser(User user) {
		
		/*IndexQuery query = new IndexQueryBuilder().withIndexName("talks").withId("/tmp").withObject(talk).build();
	    String id = esTemplate.index(query);
	    GetQuery getQuery = new GetQuery();
	    getQuery.setId(id);
	    Talk queriedObject = esTemplate.queryForObject(getQuery, Talk.class);*/

		IndexQuery userQuery =new IndexQuery();
         userQuery.setId(user.getUserId());
         userQuery.setIndexName(indexName);
         userQuery.setType(userTypeName);
         userQuery.setObject(user); 
         elasticsearchTemplate.refresh(indexName);
		return user;
	
/*		 User user1=restTemplate.postForObject(new StringJoiner("").add("http://localhost:9200"+"my_index"+"/"
				+ "user"+"/").add(user.getUserId()).add("?refresh=20").add("&timeout=20").toString(), user, User.class);
		 return user1;*/
	}

}
