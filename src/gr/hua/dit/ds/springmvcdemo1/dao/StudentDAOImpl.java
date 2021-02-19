package gr.hua.dit.ds.springmvcdemo1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.ds.springmvcdemo1.entity.Student;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudentDAOImpl implements  StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Student> getStudents() { 
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<Student> query = currentSession.createQuery("from Student", Student.class);


        // execute the query and get the results list
        List<Student> students = query.getResultList();

        //return the results
        return students;
    }
}