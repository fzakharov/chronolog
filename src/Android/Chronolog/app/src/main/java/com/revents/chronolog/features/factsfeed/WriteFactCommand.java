package com.revents.chronolog.features.factsfeed;

import com.revents.chronolog.app.Command;
import com.revents.chronolog.app.FactBuilder;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.Fact;

public class WriteFactCommand implements Command {

    private FactWriter factWriter;
    private FactBuilder factBuilder;

    public WriteFactCommand(FactWriter factWriter, FactBuilder factBuilder) {
        this.factWriter = factWriter;
        this.factBuilder = factBuilder;
    }

    @Override
    public void execute() {

        Fact fact = factBuilder.build();
        factWriter.write(fact);
    }

    //@Override
    //public void execute() {

//        try {
//            Intent intent = new Intent(context, FactTypesActivity.class);
//            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setFlags(
//                    Intent.FLAG_ACTIVITY_NEW_TASK
//                            | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//            context.startActivity(intent);
//        } catch (Exception ex) {
//            String msg = ex.toString();
//        }
    //}
}
