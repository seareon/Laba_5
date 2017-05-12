package com.seareon.servlet.command.client;

import com.seareon.servlet.command.ActionCommand;
import com.seareon.servlet.command.impl.*;

/**
 * Перечисление содержит команды, доступные пользователю
 *
 * @author Misha Ro
 * @version 1.0
 */
public enum CommandEnum {
    WRONG {
        {
            this.command = new WrongAction();
        }
    },
    SELECT {
        {
            this.command = new SelectCommand();
        }
    },
    DELETE {
        {
            this.command = new DeleteCommand();
        }
    },
    ADD {
        {
            this.command = new AddCommand();
        }
    },
    UPDATE {
        {
            this.command = new UpdateCommand();
        }
    },
    INDEX {
        {
            this.command = new IndexCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
