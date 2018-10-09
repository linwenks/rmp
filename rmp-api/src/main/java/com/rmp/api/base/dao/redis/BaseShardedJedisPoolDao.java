package com.rmp.api.base.dao.redis;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Tuple;

/**
 * 
 * @author linw
 *
 */
@Component
public class BaseShardedJedisPoolDao {
	
	protected static Logger log = LoggerFactory.getLogger(BaseShardedJedisPoolDao.class);

	@Autowired
	private ShardedJedisPool shardedJedisPool;
	
	public ShardedJedis getShardedJedis() {
		return shardedJedisPool.getResource();
	}
	
	/**
	 * 选择数据库
	 * @param index 数据库（为空默认）
	 */
	public ShardedJedis selectDB(Integer index) {
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		if (index == null) {
			return shardedJedis;
		}
		
		Collection<Jedis> collection = shardedJedis.getAllShards();
		Iterator<Jedis> jedis = collection.iterator();
		while (jedis.hasNext()) {
			// 指定数据库
			jedis.next().select(index);
		}
		return shardedJedis;
	}
	
	public Long expire(Integer index, String key, int seconds) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.expire(key, seconds);
		} catch (Exception e) {
			log.error(" jedis error : expire [key=" + key + "][seconds=" + seconds + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long ttl(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.ttl(key);
		} catch (Exception e) {
			log.error(" jedis error : expire [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public String set(Integer index, String key, String value) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.set(key, value);
		} catch (Exception e) {
			log.error(" jedis error : set [key=" + key + "][value=" + value + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public String get(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.get(key);
		} catch (Exception e) {
			log.error(" jedis error : get [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long del(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.del(key);
		} catch (Exception e) {
			log.error(" jedis error : del [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Boolean exists(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.exists(key);
		} catch (Exception e) {
			log.error(" jedis error : exists [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	// set start
	public Long sadd(Integer index, String key, String... members) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.sadd(key, members);
		} catch (Exception e) {
			log.error(" jedis error : sadd [key=" + key + "][members=" + members + "] " + e.getMessage(), e);
		}
		return null;
	} 
	
	public String spop(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.spop(key);
		} catch (Exception e) {
			log.error(" jedis error : spop [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Set<String> smembers(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.smembers(key);
		} catch (Exception e) {
			log.error(" jedis error : smembers [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Boolean sismember(Integer index, String key, String member) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.sismember(key, member);
		} catch (Exception e) {
			log.error(" jedis error : sismember [key=" + key + "][member=" + member + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long scard(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.scard(key);
		} catch (Exception e) {
			log.error(" jedis error : scard [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	// set end
	
	// hash start
	public Map<String, String> hgetAll(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hgetAll(key);
		} catch (Exception e) {
			log.error(" jedis error : hgetAll [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long hset(Integer index, String key, String field, String value) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hset(key, field, value);
		} catch (Exception e) {
			log.error(" jedis error : hset [key=" + key + "][field=" + field + "][value=" + value + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public String hget(Integer index, String key, String field) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hget(key, field);
		} catch (Exception e) {
			log.error(" jedis error : hget [key=" + key + "][field=" + field + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long hdel(Integer index, String key, String... fields) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hdel(key, fields);
		} catch (Exception e) {
			log.error(" jedis error : hdel [key=" + key + "][fields=" + fields + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Set<String> hkeys(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hkeys(key);
		} catch (Exception e) {
			log.error(" jedis error : hkeys [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public List<String> hvals(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hvals(key);
		} catch (Exception e) {
			log.error(" jedis error : hvals [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Boolean hexists(Integer index, String key, String field) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hexists(key, field);
		} catch (Exception e) {
			log.error(" jedis error : hexists [key=" + key + "][field=" + field + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public String hmset(Integer index, String key, Map<String, String> hash) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hmset(key, hash);
		} catch (Exception e) {
			log.error(" jedis error : hmset [key=" + key + "][hash=" + hash + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public List<String> hmget(Integer index, String key, String... fields) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.hmget(key, fields);
		} catch (Exception e) {
			log.error(" jedis error : hmget [key=" + key + "][fields=" + fields + "] " + e.getMessage(), e);
		}
		return null;
	}
	// hash end
	
	// z start
	public Long zadd(Integer index, String key, double score, String member) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zadd(key, score, member);
		} catch (Exception e) {
			log.error(" jedis error : zadd [key=" + key + "][score=" + score + "][member=" + member + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long zadd(Integer index, String key, Map<String, Double> scoreMembers) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zadd(key, scoreMembers);
		} catch (Exception e) {
			log.error(" jedis error : zadd [key=" + key + "][scoreMembers=" + scoreMembers + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long zcard(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zcard(key);
		} catch (Exception e) {
			log.error(" jedis error : zcard [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Set<Tuple> zrevrangeWithScores(Integer index, String key, long start, long end) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zrevrangeWithScores(key, start, end);
		} catch (Exception e) {
			log.error(" jedis error : zrevrangeWithScores [key=" + key + "][start=" + start + "][end=" + end + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long zremrangeByRank(Integer index, String key, long start, long end) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zremrangeByRank(key, start, end);
		} catch (Exception e) {
			log.error(" jedis error : zcard [key=" + key + "][start=" + start + "][end=" + end + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Long zrank(Integer index, String key, String member) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zrank(key, member);
		} catch (Exception e) {
			log.error(" jedis error : zrank [key=" + key + "][member=" + member + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Set<String> zrange(Integer index, String key, long start, long end) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zrange(key, start, end);
		} catch (Exception e) {
			log.error(" jedis error : zrank [key=" + key + "][start=" + start + "][end=" + end + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Set<Tuple> zrangeWithScores(Integer index, String key, long start, long end) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zrangeWithScores(key, start, end);
		} catch (Exception e) {
			log.error(" jedis error : zrangeWithScores [key=" + key + "][start=" + start + "][end=" + end + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Double zincrby(Integer index, String key, double score, String member) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zincrby(key, score, member);
		} catch (Exception e) {
			log.error(" jedis error : zincrby [key=" + key + "][score=" + score + "][member=" + member + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	
	public Set<String> zrangeByLex(Integer index, String key, String min, String max) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zrangeByLex(key, min, max);
		} catch (Exception e) {
			log.error(" jedis error : zrank [key=" + key + "][min=" + min + "][max=" + max + "] " + e.getMessage(), e);
		}
		return null;
	}
	 
	public Set<String> zrevrange(Integer index, String key, long start, long end) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zrevrange(key, start, end);
		} catch (Exception e) {
			log.error(" jedis error : zrevrange [key=" + key + "][start=" + start + "][end=" + end + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Set<String> zrevrangeByScore(Integer index, String key, double max, double min) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zrevrangeByScore(key, max, min);
		} catch (Exception e) {
			log.error(" jedis error : zrevrangeByScore [key=" + key + "][max=" + max + "][min=" + min + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Set<String> zrevrangeByScore(Integer index, String key, double max, double min, int offset, int count) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.zrevrangeByScore(key, max, min, offset, count);
		} catch (Exception e) {
			log.error(" jedis error : zrevrangeByScore [key=" + key + "][max=" + max + "][min=" + min + "][offset=" + offset + "][count=" + count + "] " + e.getMessage(), e);
		}
		return null;
	}
	// z end
	
	// pipelined h start
	public Response<String> pipelinedHmset(Integer index, String key, Map<String, String> hash) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.pipelined().hmset(key, hash);
		} catch (Exception e) {
			log.error(" jedis error : pipelinedHmset [key=" + key + "][hash=" + hash + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public Response<Set<String>> pipelinedSmembers(Integer index, String key) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			return shardedJedis.pipelined().smembers(key);
		} catch (Exception e) {
			log.error(" jedis error : pipelinedSmembers [key=" + key + "] " + e.getMessage(), e);
		}
		return null;
	}
	
	public void pipelinedSync(Integer index) {
		try (ShardedJedis shardedJedis = selectDB(index)) {
			shardedJedis.pipelined().sync();
		} catch (Exception e) {
			log.error(" jedis error : pipelined sync " + e.getMessage(), e);
		}
	}
	// pipelined h end
}
