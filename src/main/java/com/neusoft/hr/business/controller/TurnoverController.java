package com.neusoft.hr.business.controller;

import com.neusoft.hr.sys.annotation.Permission;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class TurnoverController {

    @Permission("/turnover")
    @RequestMapping("/toTurnover")
    public String toTurnover(){

        return "turnover";
    }

    @Permission("/turnover")
    @RequestMapping("/turnover")
    public ModelAndView turnover(@RequestParam(value = "query",required = false)Integer query){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/toTurnover");

        return modelAndView;
    }
}
