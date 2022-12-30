package com.jean.ers.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
    private static HibernateFactory hf;

    private HibernateFactory() {

    }

    public static HibernateFactory getInstance() {
        if (hf == null) hf = new HibernateFactory();
        return hf;
    }

    public SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.configure();
        return cfg.buildSessionFactory();
    }
}
