package com.seareon.util;

/**
 * Created by Misha Ro on 17.03.2017.
 */
public enum EnumWhereParams {
    ID {
        {
            this.param = "id";
        }
    },
    FIRST_NAME {
        {
            this.param = "first_name";
        }
    },
    LAST_NAME {
        {
            this.param = "last_name";
        }
    },
    GROUP_NAME {
        {
            this.param = "group_name";
        }
    },
    DEPARTMENT {
        {
            this.param = "department";
        }
    },
    AVERAGE_MARK {
        {
            this.param = "average_mark";
        }
    },
    CITY {
        {
            this.param = "city";
        }
    },
    ADDRESS {
        {
            this.param = "address";
        }
    },
    PHONE {
        {
            this.param = "phone";
        }
    };

    String param;

    public String getParam() {
        return param;
    }
}
