Ext.onReady(function(){
	Ext.create('Ext.container.Viewport',{
	layout: 'border',
	padding: '20 20 20 20',
	items:[
		{
			xtype: 'form',
			id:'search',
			layout: 'column',
			title:'Buscador',
			height: 100,
			region: 'north',
			collapsed: false,
			items:[
				{
					columnWidth: .4,
					xtype: 'combo',
					id: 'estacion',
					name: 'estacion',
					margin: '15%',
					fieldLabel: 'Estaci&oacute;n',
					typeAhead: false,
					hideTrigger: false,
					allowBlank: true,
					queryDelay: 100,
					minChar: 2,
					valueField: 'id',
					displayField: 'nombre',
					triggerAction: 'all',
					mode: 'remote',
					loadingText: 'Cargando...',
					store: Ext.create('consulta.store.estaciones')
				},
				{
					columnWidth: .4,
					xtype: 'datefield',
					margin: '15%',
					startDay: 1,
					id: 'fecha',
					name: 'fecha',
					fieldLabel: 'Fecha'
				},
				{
					columnWidth: .2,
					border: false,
					xtype: 'button',
					margin: '15%',
					text: 'Buscar',
					handler: function(){
						Ext.getCmp('listTrain').getStore().load({params: Ext.getCmp('search').getValues()});
					}		
				}
			]
		},
		{
			xtype: 'grid',
			id:'listTrain',
			title:'Listado de trenes',
			region: 'center',
			store: Ext.create('consulta.store.llegadas'),
			columns:[
				{
					header: 'Hora programada',
					flex: .25,
					dataIndex: 'hora'
				},
				{
					header: 'Hora de llegada',
					flex: .25,
					dataIndex: 'horaP'//Hora prevista
				},
				{
					header: 'Procedencia',
					flex: .25,
					dataIndex: 'procedencia'
				},
				{
					header: 'Numero de tren',
					flex: .25,
					dataIndex: 'tren'
				},
				{
					header: 'Indemnizaci&oacute;n',
					flex: .25,
					dataIndex:'indemnizacion',
					renderer: function(value, cell){
						return value?'<div style="text-align: center;"><img src="ico/accept.png"></div>':'<div style="text-align: center;"><img src="ico/cancel.png"></div>'
					}
				}
			]
		}
	]});
});