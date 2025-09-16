ALTER TABLE sensor_localizacao
    ADD CONSTRAINT fk_sensor_moto FOREIGN KEY (moto_id) REFERENCES moto (id);

ALTER TABLE moto
    ADD CONSTRAINT FK_MOTO_ON_PATIO FOREIGN KEY (patio_id) REFERENCES patio (id);