ALTER TABLE sensor_localizacao
DROP CONSTRAINT fk_sensor_moto;

ALTER TABLE sensor_localizacao
    ADD CONSTRAINT fk_sensor_moto FOREIGN KEY (moto_id) REFERENCES moto (id) ON DELETE CASCADE;