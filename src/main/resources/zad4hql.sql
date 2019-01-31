SELECT *, count(name) cn
FROM world_x.country c, world_x.countrylanguage cl
WHERE c.code = cl.countrycode
GROUP BY c.name
ORDER BY cn DESC
LIMIT 1;
-- 1
SELECT *
FROM (;
SELECT * -- , count(name) cn
	FROM world_x.country c, world_x.countrylanguage cl
	WHERE c.code = cl.countrycode
	GROUP BY c.name HAVING count(name) = 12;
	-- ORDER BY cn DESC;
    ) aa;
WHERE cn = (SELECT count(name) cn
			FROM world_x.country c, world_x.countrylanguage cl
			WHERE c.code = cl.countrycode
			GROUP BY c.name
			ORDER BY cn DESC
			LIMIT 1);
-- 2;
WITH aa AS (SELECT *, count(name) cn
			FROM world_x.country c, world_x.countrylanguage cl
			WHERE c.code = cl.countrycode
			GROUP BY c.name
			ORDER BY cn DESC)
SELECT *
FROM aa
WHERE cn = (SELECT cn FROM aa LIMIT 1);
-- uzyskanie samej liczby krajów z maksymalną ilością języków (1)
SELECT count(cn) liczba_krajów_z_maksymalną_ilością_języków
FROM (SELECT *, count(name) cn
	FROM world_x.country c, world_x.countrylanguage cl
	WHERE c.code = cl.countrycode
	GROUP BY c.name
	ORDER BY cn DESC) aa
WHERE cn = (SELECT count(name) cn
			FROM world_x.country c, world_x.countrylanguage cl
			WHERE c.code = cl.countrycode
			GROUP BY c.name
			ORDER BY cn DESC
			LIMIT 1);
-- uzyskanie samej liczby krajów z maksymalną ilością języków (2)
WITH aa AS (SELECT *, count(name) cn
			FROM world_x.country c, world_x.countrylanguage cl
			WHERE c.code = cl.countrycode
			GROUP BY c.name
			ORDER BY cn DESC)
SELECT count(cn) liczba_krajów_z_maksymalną_ilością_języków
FROM aa
WHERE cn = (SELECT cn FROM aa LIMIT 1);