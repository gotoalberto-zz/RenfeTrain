Ext.onReady(function(){
	
	var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
	    clicksToMoveEditor: 1,
	    autoCancel: false,
	    listeners: {
	    	edit:	function(editor, e){
	    		//editor.record.data;
	    	}
	    }
	});
	
	
	
	Ext.create('Ext.container.Viewport',{
	layout: 'border',
	padding: '20 20 20 20',
	items:[
		{
			xtype:	'grid',
			id: 'indemnizacionesGrid',
			title: 'Indemnizaciones por tipo de tren',
			region:	'center',
			store: Ext.create('adminIndemnizaciones.store.indemnizacion'),
			plugins:[rowEditing],
		    tbar: 	[
					{
						xtype: 'combo',
						fieldLabel: 'Tipo de Tren',
						id: 'tipoTrenCombo',
						valueField: 'id',
						displayField: 'nombre',
						store: Ext.create('adminIndemnizaciones.store.tipoTren'),
						mode: 'remote',
						typeAhead: false,
						hideTrigger: false
					},
					{
					    text: 'Nueva indemnizacion',
					    iconCls: 'add',
					    handler: function() {
					    	var r = Ext.create('adminIndemnizaciones.model.indemnizacion');
					
					        Ext.getCmp('indemnizacionesGrid').getStore().insert(0, r);
					        rowEditing.startEdit(0, 0);
					    }
					},
					{
					    text: 'Eliminar',
					    iconCls: 'cancel',
					    handler: function() {
					        var sm = grid.getSelectionModel();
					        rowEditing.cancelEdit();
					        store.remove(sm.getSelection());
					        if (store.getCount() > 0) {
					            sm.select(0);
					        }
					    }
					}
		    ],
			columns: [
			    {
		            header: 'Porcentaje Indemnizacion',
		            dataIndex: 'porcentaje',
		            flex: 1,
		            editor: {
		            	id: 'porcentaje',
		                allowBlank: false
		            }
				},
				{
		            header: 'Minutos de Retraso',
		            dataIndex: 'minutosRetraso',
		            flex: 1,
		            editor: {
		            	id: 'minutosRetraso',
		                allowBlank: false
		            }
				},
				{
		            header: 'idTipoTren',
		            dataIndex: 'idTipoTren',
		            flex: 1,
		            editor: {
		            	id: 'idTipoTren',
		                allowBlank: false
		            }
				},
				{
		            header: 'idIndemnizacion',
		            dataIndex: 'idIndemnizacion',
		            flex: 1,
		            editor: {
		            	id: 'idIndemnizacion',
		                allowBlank: false
		            }
				}
			]
		}//Fin item Grid
	]//Fin items
	});//Fin Ext.create
});//Fin Ext.onReady