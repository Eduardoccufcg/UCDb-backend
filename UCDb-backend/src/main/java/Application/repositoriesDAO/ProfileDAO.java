package Application.repositoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import Application.model.Profile;



public interface ProfileDAO extends JpaRepository<Profile, Long> {



}
