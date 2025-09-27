package kr.or.bit.utils;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@WebFilter(
    description = "어노테이션 활용 필터 처리",
    urlPatterns = "/*",
    initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8")
    }
)
public class Encoding extends HttpFilter implements Filter {

    private String encoding;

    public Encoding() {
        super();
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.encoding = fConfig.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
        // 필요 시 자원 해제 로직 작성
    }

    // AOP 관점지향 프로그래밍
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(this.encoding);
        }

        System.out.println("웹 접근시 urlPatterns = /* , 통과");

        chain.doFilter(request, response);
    }
}