package gmcc.hxs.respository;

import gmcc.hxs.dataobject.CommentCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CommentCityRepository extends JpaRepository<CommentCity,Integer> {
    ArrayList<CommentCity> findAllByCity(String city);
}
