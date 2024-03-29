//信管182 徐学印 201802104067
package Service;

import dao.SchoolDao;
import domain.School;
import domain.Department;
import java.sql.SQLException;
import java.util.Collection;

public final class SchoolService {
	private static SchoolDao schoolDao= SchoolDao.getInstance();
	private static SchoolService schoolService=new SchoolService();


	public static SchoolService getInstance(){
		return schoolService;
	}

	public Collection<School> findAll() throws SQLException {
		return schoolDao.findAll();
	}

	public School find(Integer id) throws SQLException {
		return schoolDao.find(id);
	}

	public boolean update(School school) throws SQLException, ClassNotFoundException {
		return schoolDao.update(school);
	}

	public boolean add(School school) throws SQLException {
		return schoolDao.add(school);
	}

	public String delete(Integer id) throws SQLException {
		School school = this.find(id);
		return this.delete(school);
	}

	public String delete(School school) throws SQLException {
		//获得所有下一级单位（Department）
		Collection<Department> departmentSet =
				DepartmentService.getInstance().getAll(school);
		//若没有二级单位，则能够删除
		if(departmentSet.size()==0){
			schoolDao.delete(school);
			return "DELETED";
		}else {
			return "{\"result\":\"having departments\"}";
		}
	}
}
