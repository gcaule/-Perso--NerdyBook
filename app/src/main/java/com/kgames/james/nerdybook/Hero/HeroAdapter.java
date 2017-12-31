package com.kgames.james.nerdybook.Hero;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kgames.james.nerdybook.AdventureTalisman;
import com.kgames.james.nerdybook.MainMenu;
import com.kgames.james.nerdybook.R;

import java.util.List;

/**
 * Created by james on 17/12/17.
 */

public class HeroAdapter extends ArrayAdapter<HeroModel> {

    Context mContext;
    int layoutRes;
    List<HeroModel> mHeroList;

    HeroDBHelper mDBHelper;

    public HeroAdapter(Context mContext, int layoutRes, List<HeroModel> mHeroList, HeroDBHelper mDBHelper) {
        super(mContext, layoutRes, mHeroList);

        this.mContext = mContext;
        this.layoutRes = layoutRes;
        this.mHeroList = mHeroList;
        this.mDBHelper = mDBHelper;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.saved_list_row, parent, false);
        }

        SavedGamesViewHolder viewHolder = (SavedGamesViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SavedGamesViewHolder();

            viewHolder.savedHeroName = convertView.findViewById(R.id.saved_hero_value);

            viewHolder.savedAdventure = convertView.findViewById(R.id.saved_adventure_value);
            viewHolder.savedTotalChapters = convertView.findViewById(R.id.saved_chapters_value);
            viewHolder.savedDifficulty = convertView.findViewById(R.id.saved_difficulty_value);

            viewHolder.savedAbility = convertView.findViewById(R.id.saved_ability_value);
            viewHolder.savedStamina = convertView.findViewById(R.id.saved_stamina_value);
            viewHolder.savedLuck = convertView.findViewById(R.id.saved_luck_value);

            convertView.setTag(viewHolder);
        }


        final HeroModel heroModel = mHeroList.get(position);

        viewHolder.savedHeroName.setText(heroModel.getName());

        viewHolder.savedAdventure.setText(heroModel.getAdventure());
        viewHolder.savedTotalChapters.setText(String.valueOf(heroModel.getTotalChapters()));
        viewHolder.savedDifficulty.setText(heroModel.getDifficulty());

        viewHolder.savedAbility.setText(String.format("%s (%s)", heroModel.getAbilityCurrent(), heroModel.getAbilityMax()));
        viewHolder.savedStamina.setText(String.format("%s (%s)", heroModel.getStaminaCurrent(), heroModel.getStaminaMax()));
        viewHolder.savedLuck.setText(String.format("%s (%s)", heroModel.getLuckCurrent(), heroModel.getLuckMax()));

        convertView.findViewById(R.id.load_saved_games).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AdventureTalisman.class);
                intent.putExtra("HeroID", heroModel.getID());
                intent.putExtra("CurrentChapter", heroModel.getCurrentChapter());
                intent.putExtra("TotalChapters", heroModel.getTotalChapters());
                getContext().startActivity(intent);
            }
        });

        convertView.findViewById(R.id.delete_saved_games).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteHero(heroModel);

            }
        });

        if (mHeroList.size() == 0) {
            Intent intent = new Intent(getContext(), MainMenu.class);
            getContext().startActivity(intent);
        }

        return convertView;
    }

    private class SavedGamesViewHolder{

        public TextView savedHeroName;

        public TextView savedAdventure;
        public TextView savedTotalChapters;
        public TextView savedDifficulty;

        public TextView savedAbility;
        public TextView savedStamina;
        public TextView savedLuck;

    }


    private void deleteHero(final HeroModel heroModel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle(R.string.delete_dialog_title);
        builder.setMessage(R.string.delete_dialog_message);
        builder.setPositiveButton(R.string.delete_dialog_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (mDBHelper.deleteHero(Integer.parseInt(heroModel.getID())))
                    loadHeroesFromDatabaseAgain();
            }
        });

        builder.setNegativeButton(R.string.delete_dialog_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadHeroesFromDatabaseAgain() {

        Cursor cursor = mDBHelper.getAllHeroes();
        mHeroList.clear();

        if (cursor.moveToFirst()) {
            do {
                mHeroList.add(new HeroModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        cursor.getInt(9),
                        cursor.getInt(10),
                        cursor.getInt(11)
                ));
            } while (cursor.moveToNext());
        }
        notifyDataSetChanged();
    }

}
