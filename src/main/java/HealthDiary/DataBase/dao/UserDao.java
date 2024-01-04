package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.exceptions.NoDataFound;

public class UserDao extends BaseDao implements DML {

    private DbUser user;

    public UserDao(){
        super();
    }

    public UserDao(DbUser user){
        super();
        this.user = user;
    }


    @Override
    public void insert() {
        if(user != null){
            this.getSession().persist(user);
        }
    }

    @Override
    public void update() {
        // CHeck existence
        DbUser dbUser = findById(user.getId());

        this.getSession().merge(user);
    }

    @Override
    public void delete() {
        this.getSession().remove(user);
    }

    public DbUser findById(Long user_id) {

        DbUser dbUser = this.getSession().get(DbUser.class, user_id);
        if (dbUser == null){
            throw new NoDataFound("No user with id \"" + user_id + "\"");
        }

        return dbUser;
    }
}
