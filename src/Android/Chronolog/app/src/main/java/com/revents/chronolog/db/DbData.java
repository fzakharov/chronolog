package com.revents.chronolog.db;

import android.graphics.Color;

import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

public class DbData {
    public static final Boolean DISABLED = false;

    public static class Values {
        public static final long DEFAULT = 1L;

        public static final ValueDescriptor[] ITEMS = new ValueDescriptor[]{
                new ValueDescriptor(DEFAULT, "Число", "Событие со значением типа целое число.", "default", ""),
        };
    }

    public static class Groups {
        public static final long SLEEP = 10L;
        public static final long FOOD = 20L;
        public static final long Physical_Activity = 30L;
        public static final long Hygiene = 40L;
        public static final long Health = 50L;
        public static final long OTHER = 60L;

        public static final FactTypeGroup[] ITEMS = new FactTypeGroup[]{
                Create(OTHER, "Разное", ""),
                Create(SLEEP, "Сон", ""),
                Create(FOOD, "Питание", ""),
                Create(Physical_Activity, "Физическая активность", ""),
                Create(Hygiene, "Гигиена", ""),
                Create(Health, "Здоровье", ""),
        };

        private static FactTypeGroup Create(long id, String name, String descr) {
            return new FactTypeGroup(id, name, descr, Color.TRANSPARENT);
        }
    }

    public static class Types {
        public static final Long WAKEUP = Groups.SLEEP * 10 + 10L;
        public static final Long TO_SLEEP = Groups.SLEEP * 10 + 20L;

        public static final Long Breakfast = Groups.FOOD * 10 + 10L;
        public static final Long Lunch = Groups.FOOD * 10 + 20L;
        public static final Long Evening_meal = Groups.FOOD * 10 + 30L;
        public static final Long Quick_bite = Groups.FOOD * 10 + 40L;
        public static final Long Coffee = Groups.FOOD * 10 + 50L;

        public static final Long Physical_charge = Groups.Physical_Activity * 10 + 10;
        public static final Long Classes_on_the_bar = Groups.Physical_Activity * 10 + 20;
        public static final Long Classes_on_parallel_bars = Groups.Physical_Activity * 10 + 30;

        public static final Long Dental_floss = Groups.Hygiene * 10 + 10;

        public static final Long Headache = Groups.Health * 10 + 10;
        public static final Long Emotional_outburst = Groups.Health * 10 + 20;

        public static final Long Become_tempered = Groups.OTHER * 10 + 10;
        public static final Long Meditation = Groups.OTHER * 10 + 20;

        public static final FactType[] ITEMS = new FactType[]{
                Create(WAKEUP, "Проснулся", "1-5: Качество сна", Groups.SLEEP),
                Create(TO_SLEEP, "Лег спать", "1-5: Самочусвтие в конце дня", Groups.SLEEP),

                Create(Breakfast, "Позавтракал", "1-5: Качество завтрака", Groups.FOOD),
                Create(Lunch, "Пообедал", "1-5: Качество обеда", Groups.FOOD),
                Create(Evening_meal, "Поужинал", "1-5: Качество ужина", Groups.FOOD),
                Create(Quick_bite, "Перекусил", "1-5: Качество перекуса", Groups.FOOD),
                Create(Coffee, "Кофе", "", Groups.FOOD),

                Create(Physical_charge, "Физическая зарядка", "", Groups.Physical_Activity),
                Create(Classes_on_the_bar, "Занятия на турнике", "Количество раз", Groups.Physical_Activity),
                Create(Classes_on_parallel_bars, "Занятия на брусьях", "Количество раз", Groups.Physical_Activity),

                Create(Dental_floss, "Нить", "Зубная нить", Groups.Hygiene),

                Create(Headache, "Головная боль", "1-5: 1-легко, 5-очень сильно", Groups.Health),
                Create(Emotional_outburst, "Эмоциональный всплеск", "1-5: 1-слабый, 5-сильный", Groups.Health),

                Create(Become_tempered, "Закалялся", "", Groups.OTHER),
                Create(Meditation, "Медитация", "", Groups.OTHER),
        };

        private static FactType Create(Long id, String name, String descr, long factTypeGroupId) {
            return new FactType(id, name, descr, DISABLED, factTypeGroupId, Values.DEFAULT);
        }
    }

    ;
}

