package com.fr.adaming.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.configuration.LogConfig;
import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.util.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao dao;
	private Logger log = LogConfig.getLogger(UserService.class);

//	@Autowired
//	public UserService(IUserDao dao) {
//		super();
//		this.dao = dao;
//	}

	public IUserDao getDao() {
		return dao;
	}

	@Override
	public User login(String login, String pwd) {
		User ret = dao.findByLoginAndPwd(login, pwd);

		if (ret == null) {
			log.debug("incorrect login/pwd, failed to login");
			return null;
		} else {
			log.debug("login success");
			return ret;
		}
	}

	@Override
	public User save(User entity) {
		User u = null;
		try {

			if (entity.getId() == null || entity.getId() == 0 || !dao.existsById(entity.getId())) {
				log.debug("succes insert");
				try {
					u = dao.save(entity);

				} catch (DataIntegrityViolationException e) {
					log.error("error, login already existing, failed to insert ", e);
					return null;
				}
				return u;
			} else {
				log.debug("error insert");
				return null;
			}
		} catch (Exception e) {
			log.error("error with input parameter ", e);
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public User findById(Long id) {
		User u = null;
		try {
			u = dao.findById(id).get();
			return u;

		} catch (Exception e) {
			if (e instanceof NoSuchElementException) {
				log.error("db entry does not exist ", e);
				return null;

			} else if (e instanceof InvalidDataAccessApiUsageException) {
				log.error("provided id is null " + e);
				return null;

			} else {
				log.error("unexpected exception when finding", e);
				return null;
			}
		}
	}

	@Override
	public List<User> findByTeamId(Long id) {
		List<User> res=null;
		if(id==null) {
			log.error("i");
			return null;
		}
		try {
			res = dao.findByTeamId(id);
			return res;

		} catch (Exception e) {
			if (e instanceof NoSuchElementException) {
				log.error("db entry does not exist ", e);
				return null;

			} else if (e instanceof InvalidDataAccessApiUsageException) {
				log.error("provided id is null " + e);
				return null;

			} else {
				log.error("unexpected exception when finding", e);
				return null;
			}
		}
	}

	@Override
	public User update(User user) {
		User u = null;
		try {

			if (user.getId() != null && user.getId() != 0 && dao.existsById(user.getId())) {
				try {
					u = dao.save(user);
					log.debug("succes update");
					return u;

				} catch (Exception e) {
					if (e instanceof DataIntegrityViolationException) {
						log.error("constraint not validated ", e);
						return null;
					} else {
						log.error("unexpected exception ", e);
						return null;
					}
				}
			} else {
				log.debug("error update id null or 0 or not existing");
				return null;
			}
		} catch (Exception e) {
			log.error("invalid parameters ", e);
			return null;
		}
	}

	@Override
	public boolean delete(Long id) {
		if (id == null) {
			log.warn("id null, aborting deletion...");
			return false;
		}

		try {
			dao.deleteById(id);

		} catch (Exception e) {
			log.error("exception occured when deleting ", e);
			return false;
		}

		return !dao.existsById(id);
	}

}
