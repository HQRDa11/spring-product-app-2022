package fr.greta91.springProductApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.greta91.springProductApp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	//@Query("select u from User u where u.username=?1")
	User findByUsername(String username);

}
