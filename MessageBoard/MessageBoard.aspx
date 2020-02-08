<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="ajaxToolkit" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
</head>
<body>
    <h1>Nes' Message Board</h1>
    <form id="form1" runat="server" style="margin: 10px">
        <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>

        <div class="form-group">
            <asp:Label ID="NameLbl" runat="server" Text="Name"></asp:Label>
            <asp:TextBox ID="NameTB" runat="server" CssClass="form-control"></asp:TextBox>
            <asp:RequiredFieldValidator runat="server" ControlToValidate="NameTB" ErrorMessage="Name field is required" ForeColor="Red" ValidationGroup="mainValidation" />
        </div>

        <div class="form-group">
            <asp:Label ID="SubjectLbl" runat="server" Text="Subject"></asp:Label>
            <asp:TextBox ID="SubjectTB" runat="server" CssClass="form-control"></asp:TextBox>
            <asp:RequiredFieldValidator runat="server" ControlToValidate="SubjectTB" ErrorMessage="Subject field is required" ForeColor="Red" ValidationGroup="mainValidation" />
        </div>

        <div class="form-group">
            <asp:Label ID="MessageLbl" runat="server" Text="Message"></asp:Label>
            <asp:TextBox ID="MessageTB" runat="server" TextMode="MultiLine" Columns="40" Rows="4" CssClass="form-control"></asp:TextBox>
            <asp:RequiredFieldValidator runat="server" ControlToValidate="MessageTB" ErrorMessage="Message field is required" ForeColor="Red" ValidationGroup="mainValidation" />
        </div>

        <asp:Button ID="PostBtn" runat="server" Text="Post Message" CssClass="btn btn-primary" OnClick="PostBtn_Click" ValidationGroup="mainValidation" />
        <asp:Label ID="confirmationLbl" runat="server" Text="Your message was succesfully created!" Visible="false" Font-Size="Large"></asp:Label>

        <asp:UpdatePanel ID="UpdatePanel1" runat="server" UpdateMode="Conditional" ChildrenAsTriggers="true">
            <ContentTemplate>
                <div class="container">
                    <asp:ListView ID="ListView1" runat="server" OnItemDataBound="ListView1_ItemDataBound"
                        OnPagePropertiesChanged="ListView1_PagePropertiesChanged" InsertItemPosition="None" OnItemEditing="ListView1_ItemEditing" OnItemUpdating="ListView1_ItemUpdating">
                        <ItemTemplate>
                            <asp:Panel ID="Panel1" runat="server" Visible="false">
                                <h5>
                                    <asp:Label runat="server" Text='<%#Eval("Name")%>' /></h5>
                                <div class="border rounded mb-3 shadow-sm" style="background-color: white">
                                    <asp:Label runat="server" Text='<%#Eval("Subject")%>' />
                                    <div class="border m-2">
                                        <asp:Label runat="server" Text='<%#Eval("Message")%>'></asp:Label>
                                    </div>
                                    <asp:Button ID="replyToBtn" runat="server" CommandArgument='<%#Eval("ID")%>' Text="Reply" CommandName="Edit" CssClass="btn btn-secondary" />
                                </div>
                            </asp:Panel>

                            <asp:Panel ID="Panel2" runat="server" Style="margin-left: 30px" Visible="false">
                                <h6>
                                    <asp:Label runat="server" Text='<%#Eval("Name")%>' /></h6>
                                <div class="border rounded mb-3 shadow-sm" style="background-color: white">
                                    <asp:Label runat="server" Text='<%#Eval("Subject")%>' />
                                    <div class="border m-2">
                                        <asp:Label runat="server" Text='<%#Eval("Message")%>'></asp:Label>
                                    </div>
                                </div>
                            </asp:Panel>

                        </ItemTemplate>

                        <EditItemTemplate>
                            <h5>
                                <asp:Label runat="server" Text='<%#Eval("Name")%>' /></h5>
                            <div class="border rounded mb-3 shadow-sm" style="background-color: white">
                                <asp:Label runat="server" Text='<%#Eval("Subject")%>' />
                                <div class="border m-2">
                                    <asp:Label runat="server" Text='<%#Eval("Message")%>'></asp:Label>
                                </div>
                            </div>
                            <div id="replyForm">
                                <div class="form-group">
                                    <asp:Label ID="NameLbl2" runat="server" Text="Name"></asp:Label>
                                    <asp:TextBox ID="NameTB2" runat="server" CssClass="form-control"></asp:TextBox>
                                    <asp:RequiredFieldValidator runat="server" ControlToValidate="NameTB2" ErrorMessage="Name field is required" ForeColor="Red" ValidationGroup="replyValidation" />
                                </div>

                                <div class="form-group">
                                    <asp:Label ID="SubjectLbl2" runat="server" Text="Subject"></asp:Label>
                                    <asp:TextBox ID="SubjectTB2" runat="server" CssClass="form-control"></asp:TextBox>
                                    <asp:RequiredFieldValidator runat="server" ControlToValidate="SubjectTB2" ErrorMessage="Subject field is required" ForeColor="Red" ValidationGroup="replyValidation" />
                                </div>

                                <div class="form-group">
                                    <asp:Label ID="MessageLbl2" runat="server" Text="Message"></asp:Label>
                                    <asp:TextBox ID="MessageTB2" runat="server" TextMode="MultiLine" Columns="40" Rows="4" CssClass="form-control"></asp:TextBox>
                                    <asp:RequiredFieldValidator runat="server" ControlToValidate="MessageTB2" ErrorMessage="Message field is required" ForeColor="Red" ValidationGroup="replyValidation" />
                                </div>

                                <asp:Button ID="PostBtn2" CommandArgument='<%#Eval("ID")%>' runat="server" Text="Post Reply" CssClass="btn btn-secondary" CommandName="Update" ValidationGroup="replyValidation" />
                                <asp:Label ID="confirmationLbl2" runat="server" Text="Your reply was succesfully created!" Visible="false" Font-Size="Large"></asp:Label>
                            </div>
                        </EditItemTemplate>
                    </asp:ListView>

                    <div>
                        <asp:DataPager ID="DataPager1" runat="server" PagedControlID="ListView1" PageSize="5">
                            <Fields>
                                <asp:NextPreviousPagerField ButtonType="Button"
                                    ShowFirstPageButton="true"
                                    ShowNextPageButton="false"
                                    ShowPreviousPageButton="false" />
                                <asp:NumericPagerField ButtonCount="10" />
                                <asp:NextPreviousPagerField ButtonType="Button"
                                    ShowLastPageButton="true"
                                    ShowNextPageButton="false"
                                    ShowPreviousPageButton="false" />
                            </Fields>
                        </asp:DataPager>
                    </div>
                </div>
            </ContentTemplate>
        </asp:UpdatePanel>

    </form>
</body>
</html>
