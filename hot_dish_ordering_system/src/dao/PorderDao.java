package dao;

import model.Porder;
import java.util.List;

public interface PorderDao {
    boolean addPorder(Porder porder);
    List<Porder> getAllPorders();
    boolean updatePorder(int id, Porder porder); 
    boolean deletePorder(int id); 
}
