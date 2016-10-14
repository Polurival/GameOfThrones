package home.skwmium.skilltest.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import home.skwmium.skilltest.R;
import home.skwmium.skilltest.databinding.ItemListCharacterBinding;
import home.skwmium.skilltest.model.dto.Character;

public class AdapterCharacters extends RecyclerView.Adapter<AdapterCharacters.ViewHolder> {
    public interface OnItemCLickListener {
        void onItemClick(Character viewModel);
    }

    @NonNull
    private List<Character> characterList = new ArrayList<>();
    @Nullable
    private OnItemCLickListener mItemCLickListener;

    public void setItemCLickListener(@Nullable OnItemCLickListener itemCLickListener) {
        mItemCLickListener = itemCLickListener;
    }

    public void setItems(List<Character> characterList) {
        if (characterList == null) {
            this.characterList.clear();
        } else {
            this.characterList = characterList;
        }
        notifyDataSetChanged();
    }

    private Character getItem(int position) {
        return characterList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListCharacterBinding characterBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_list_character,
                parent, false);
        return new ViewHolder(characterBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Character character = getItem(position);

        holder.getCharacterBinding().setCharacter(character);
        holder.getCharacterBinding().getRoot().setOnClickListener(view -> {
            if (mItemCLickListener != null)
                mItemCLickListener.onItemClick(character);
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }


    // ---------- HOLDER ----------
    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemListCharacterBinding characterBinding;

        ViewHolder(View view) {
            super(view);
            characterBinding = DataBindingUtil.bind(view);
        }

        ItemListCharacterBinding getCharacterBinding() {
            return characterBinding;
        }
    }
}
