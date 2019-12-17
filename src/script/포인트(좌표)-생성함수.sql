CREATE OR REPLACE FUNCTION FN_GET_Point(x numeric, y numeric) 
returns GEOMETRY
LANGUAGE 'plpgsql'

as $body$
DECLARE
    GEOM GEOMETRY;

BEGIN
	SELECT ST_Transform(ST_GeomFromText('POINT ('|| x || ' ' || y ||')', 4326), 5181) INTO GEOM;
	RETURN GEOM;

EXCEPTION
	WHEN others THEN
	RAISE notice
	'exception occurred! % : %',sqlstate,sqlerrm;
	RETURN NULL;
end;
$body$;
