using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            ListView1.DataSource = DatabaseAccess.GetOrderedPosts();
            ListView1.DataBind();
        }
        
    }



    protected void PostBtn_Click(object sender, EventArgs e)
    {
        confirmationLbl.Visible = false;
        DateTime date = DateTime.Now;
        string id = (DatabaseAccess.getNumberOfMessages() + 1).ToString();

        bool successful = DatabaseAccess.CreateNewPost(id, "*******", NameTB.Text, SubjectTB.Text, MessageTB.Text, date);

        if (successful)
        {
            confirmationLbl.Visible = true;
        }

        NameTB.Text = "";
        SubjectTB.Text = "";
        MessageTB.Text = "";

        ListView1.DataSource = DatabaseAccess.GetOrderedPosts();
        ListView1.DataBind();
    }

    protected void ListView1_ItemDataBound(object sender, ListViewItemEventArgs e)
    {
        if (e.Item.ItemType == ListViewItemType.DataItem)
        {
            DataRowView rowView = e.Item.DataItem as DataRowView;
            string replyToID = rowView["ReplyToID"].ToString();

            if (replyToID != "")
            {
                ((Panel)e.Item.FindControl("Panel2")).Visible = true;
            }
            else
            {
                if(((Panel)e.Item.FindControl("Panel1")) != null)
                {
                    ((Panel)e.Item.FindControl("Panel1")).Visible = true;
                }
            }
        }
    }

    protected void ListView1_PagePropertiesChanged(object sender, EventArgs e)
    {
        ListView1.InsertItemPosition = InsertItemPosition.None;

        ListView1.DataSource = DatabaseAccess.GetOrderedPosts();
        ListView1.DataBind();
    }

    /*
    protected void PostReplyBtn_Click(object sender, EventArgs e)
    {
        confirmationLbl.Visible = false;
        DateTime date = DateTime.Now;
        string id = (DatabaseAccess.getNumberOfMessages() + 1).ToString();
        string replyToID = ((Button)sender).CommandArgument.ToString();

        bool successful = DatabaseAccess.CreateNewReply(id, replyToID, "NesM", NameTB.Text, SubjectTB.Text, MessageTB.Text, date);

        if (successful)
        {
            confirmationLbl.Visible = true;
        }

        NameTB.Text = "";
        SubjectTB.Text = "";
        MessageTB.Text = "";

        ListView1.DataSource = DatabaseAccess.GetOrderedPosts();
        ListView1.DataBind();
    } */

    protected void ListView1_ItemEditing(object sender, ListViewEditEventArgs e)
    {
        ListView1.EditIndex = e.NewEditIndex;

        ListView1.DataSource = DatabaseAccess.GetOrderedPosts();
        ListView1.DataBind();
    }

    protected void ListView1_ItemUpdating(object sender, ListViewUpdateEventArgs e)
    {
        string name = (ListView1.Items[e.ItemIndex].FindControl("NameTB2") as TextBox).Text;
        string subject = (ListView1.Items[e.ItemIndex].FindControl("SubjectTB2") as TextBox).Text;
        string message = (ListView1.Items[e.ItemIndex].FindControl("MessageTB2") as TextBox).Text;
        string id = (DatabaseAccess.getNumberOfMessages() + 1).ToString();
        string replyID = (ListView1.Items[e.ItemIndex].FindControl("PostBtn2") as Button).CommandArgument;
        DateTime date = DateTime.Now;

        DatabaseAccess.CreateNewReply(id, replyID, "*******", name, subject, message, date);

        ListView1.EditIndex = -1;

        ListView1.DataSource = DatabaseAccess.GetOrderedPosts();
        ListView1.DataBind();
    }
}
