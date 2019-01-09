package com.example.akshitagupta.project3c;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListItemsFragment extends ListFragment
{
    public static final String ARRAY_ID_EXTRA_KEY = "Array_ID_Extra_Key";
    private ListItemClickListener listItemClickListener;


    //
    // This specifies the interface that the activity that contains
    // this fragment must implement.
    //
    public interface ListItemClickListener {
        void listItemClicked(int position);
    }

    @Override
    public void onAttach(Context activity)
    {
        super.onAttach(activity);

        //Cast the containing Activity to the correct type to make sure it
        //has implemented listItemClicked(int).
        try {
            listItemClickListener = (ListItemClickListener) activity;
        } catch (ClassCastException exception) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListItemClickListener");
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id)
    {
        //Mark the selected item as checked
        setItemChecked(position);
        listItemClickListener.listItemClicked(position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Make sure the fragment is retained on configuration changes.
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //
    // onActivityCreated: This method prepares the ListView to be displayed by
    // extracting the items to be displayed from the bundle and creating an
    // adapter based on them.
    //
    @Override
    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);

        String[] listItemNames = null;
        Bundle arguments = getArguments();

        //If the arguments bundle is not null, get the list item names from it.
        if(arguments != null)
            listItemNames = arguments.getStringArray(ARRAY_ID_EXTRA_KEY);

        //If no list items where provided create an empty array.
        if(listItemNames == null)
            listItemNames = new String[]{};

        //Create and set a list adapter to display the list items.
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_list_item, listItemNames);
        setListAdapter(listAdapter);

        //Make sure only one item can be checked at a time.
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    //
    // setItemChecked: This method marks the list item at the specified
    // position as checked.
    //
    public void setItemChecked(int position)
    {
        //Make sure the view has been created
        if(getView() != null)
            getListView().setItemChecked(position, true);
    }

    //
    // clearListItemSelection: This method clears the selection from
    // the ListView.
    //
    public void clearListItemSelection()
    {
        //Make sure the view has been created
        if(getView() != null) {
            int position = getListView().getCheckedItemPosition();
            getListView().setItemChecked(position, false);
        }
    }
}
