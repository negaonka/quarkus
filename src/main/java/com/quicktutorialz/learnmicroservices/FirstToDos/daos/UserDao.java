package com.quicktutorialz.learnmicroservices.FirstToDos.daos;

import java.util.Optional;
import com.quicktutorialz.learnmicroservices.FirstToDos.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface UserDao extends PanacheRepository<User> {

    //name strategy method
    Optional<User> findUserByEmail(String email);
	/*
	 * //@Query annotation equivalent
	 * 
	 * @QueryParam(value="SELECT * FROM users WHERE email=:email", nativeQuery =
	 * true) Optional<User> findUserByTheEmail(@Param("email") String email);
	 */

    //alternative already implemented by the extended interfaces: JpaRepository, PagingAndSortingRepository, CrudRepository
    // User findOne(String email);


}
