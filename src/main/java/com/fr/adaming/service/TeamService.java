package com.fr.adaming.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.configuration.LogConfig;
import com.fr.adaming.dao.ITeamDao;
import com.fr.adaming.entity.Team;
import com.fr.adaming.service.util.ITeamService;

@Service
public class TeamService implements ITeamService {

	private final ITeamDao dao;
	private Logger log = LogConfig.getLogger(TeamService.class);

	@Autowired
	public TeamService(ITeamDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Team save(Team entity) {
		Team t = null;
		try {

			if (entity.getId() == null || entity.getId() == 0 || !dao.existsById(entity.getId())) {
				log.debug("succes insert");
				try {
					t = dao.save(entity);

				} catch (DataIntegrityViolationException e) {
					log.error("error, login already existing, failed to insert ", e);
					return null;
				}
				return t;
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
	public List<Team> findAll() {
		return dao.findAll();
	}

	@Override
	public Team findById(Long id) {
		
		Team t = null;
		try {
			t = dao.findById(id).get();
			return t;

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

	@Override
	public Team update(Team entity) {
		Team t = null;
		try {

			if (entity.getId() != null && entity.getId() != 0 && dao.existsById(entity.getId())) {
				try {
					t = dao.save(entity);
					log.debug("succes update");
					return t;

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

}
