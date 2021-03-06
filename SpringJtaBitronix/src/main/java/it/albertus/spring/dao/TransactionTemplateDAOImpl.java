package it.albertus.spring.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionTemplateDAOImpl extends BaseDAO implements TransactionTemplateDAO {

	private static final Log log = LogFactory.getLog(TransactionTemplateDAOImpl.class);

	@Override
	public boolean test(Long param) throws IllegalAccessException {
		Map<String, String> params = new HashMap<String, String>();
		Calendar sysdate = Calendar.getInstance();
		for (int i = 1; i <= 5; i++) {
			params.clear();

			params.put("un", i + "-1-" + sysdate.getTimeInMillis());
			params.put("pw", i + "-2-" + sysdate.getTimeInMillis());
			params.put("nm", i + "-3-" + sysdate.getTimeInMillis());
			params.put("cg", i + "-4-" + sysdate.getTimeInMillis());
			albAdminJdbcOperations.update("INSERT INTO utenti (username, cognome, nome, password) VALUES (:un, :cg, :nm, :pw)", params);
			log.info("Insert eseguita con " + albAdminJdbcOperations + '.');

			params.put("txt", i + "-" + sysdate.getTimeInMillis());
			hrJdbcOperations.update("INSERT INTO jta_bitronix_test (id, testo) VALUES (seq_jta_bitronix_test.NEXTVAL, :txt)", params);
			log.info("Insert eseguita con " + hrJdbcOperations + '.');
		}
		// throw new IllegalArgumentException("Test rollback!");
		// throw new IllegalAccessException("Test rollback!");
		return true;
	}

}
