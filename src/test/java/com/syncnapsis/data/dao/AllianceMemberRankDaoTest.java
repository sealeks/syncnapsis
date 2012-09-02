package com.syncnapsis.data.dao;

import java.util.List;

import com.syncnapsis.data.dao.hibernate.AllianceMemberRankDaoHibernate;
import com.syncnapsis.data.model.AllianceMemberRank;
import com.syncnapsis.data.model.Empire;
import com.syncnapsis.tests.GenericDaoTestCase;
import com.syncnapsis.tests.annotations.TestCoversClasses;

@TestCoversClasses( { AllianceMemberRankDao.class, AllianceMemberRankDaoHibernate.class })
public class AllianceMemberRankDaoTest extends GenericDaoTestCase<AllianceMemberRank, Long>
{
	private AllianceMemberRankDao	allianceMemberRankDao;
	private AllianceDao		allianceDao;
	private EmpireDao empireDao;

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();

		Long existingId = allianceMemberRankDao.getAll().get(0).getId();

		AllianceMemberRank allianceMemberRank = new AllianceMemberRank();
		allianceMemberRank.setRankName("name");
		allianceMemberRank.setAlliance(allianceDao.getAll().get(0));

		setEntity(allianceMemberRank);

		setEntityProperty("rankName");
		setEntityPropertyValue("any name2");

		setExistingEntityId(existingId);
		setBadEntityId(-1L);

		setGenericDao(allianceMemberRankDao);
	}
	
	public void testGetByEmpire() throws Exception
	{
		Empire empire = empireDao.getByName("emp10");
		List<AllianceMemberRank> result = allianceMemberRankDao.getByEmpire(empire.getId());
		
		assertNotNull(result);
		assertEquals(2, result.size());
		
		for(AllianceMemberRank allianceMemberRank: result)
		{
			assertTrue(allianceMemberRank.getEmpires().contains(empire));			
		}
	}
}
