package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;


@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Produto> json (@RequestParam(value="data", required=false) String dataLancamento ) throws ParseException{	
		
		if(dataLancamento != null) {			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Calendar data = Calendar.getInstance();     
			data.setTime(formato.parse(dataLancamento));
			return dao.findByDate(data);		
		} 
		
		return dao.listar();
	}
}	
