/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
FacesContextfacesContext = FacesContext.getCurrentInstance(); 
dataTable = (HtmlDataTable) facesContext.getApplication().createComponent(HtmlDataTable.COMPONENT_TYPE) 
DataModelmyDataModel = new ResultSetDataModel(myResultSet); 
myDataModel.addDataModelListener( new DataModelListener() 
{ publicvoidrowSelected(DataModelEvent e){ 
        FacesContext.getCurrentInstance().getExternalContext().log("fila seleccionada:" + 
                e.getRowIndex()); } }); dataTable.setValue(myDataModel); 


