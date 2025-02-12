package service.impl;

import dao.PorderDao;
import dao.impl.PorderDaoImpl;
import model.Porder;
import service.PorderService;
import java.util.List;

public class PorderServiceImpl implements PorderService {
    private PorderDao porderDao = new PorderDaoImpl();

    @Override
    public boolean addPorder(Porder porder) {
        return porderDao.addPorder(porder);
    }

    @Override
    public List<Porder> getAllPorders() {
        return porderDao.getAllPorders();
    }

    @Override
    public boolean updatePorder(int id, Porder porder) {
        return porderDao.updatePorder(id, porder); 
    }

    @Override
    public boolean deletePorder(int id) {
        return porderDao.deletePorder(id); 
    }
}
