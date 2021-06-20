package com.example.mandiritest.core.repository

import com.example.mandiritest.model.ApiResponse
import com.example.mandiritest.model.ApiSuccessResponse
import com.example.mandiritest.model.ArticleResponse
import com.example.mandiritest.model.SourceResponse
import com.google.gson.Gson

class NewsMockRepository : NewsRemoteRepository {
    override suspend fun getSources(country: String?, category: String?, language: String?): ApiResponse<SourceResponse> {
        val json = "{\"status\":\"ok\",\"sources\":[{\"id\":\"argaam\",\"name\":\"Argaam\",\"description\":\"ارقام موقع متخصص في متابعة سوق الأسهم السعودي تداول - تاسي - مع تغطيه معمقة لشركات واسعار ومنتجات البتروكيماويات , تقارير مالية الاكتتابات الجديده \",\"url\":\"http://www.argaam.com\",\"category\":\"business\",\"language\":\"ar\",\"country\":\"sa\"},{\"id\":\"australian-financial-review\",\"name\":\"Australian Financial Review\",\"description\":\"The Australian Financial Review reports the latest news from business, finance, investment and politics, updated in real time. It has a reputation for independent, award-winning journalism and is essential reading for the business and investor community.\",\"url\":\"http://www.afr.com\",\"category\":\"business\",\"language\":\"en\",\"country\":\"au\"},{\"id\":\"bloomberg\",\"name\":\"Bloomberg\",\"description\":\"Bloomberg delivers business and markets news, data, analysis, and video to the world, featuring stories from Businessweek and Bloomberg News.\",\"url\":\"http://www.bloomberg.com\",\"category\":\"business\",\"language\":\"en\",\"country\":\"us\"},{\"id\":\"business-insider\",\"name\":\"Business Insider\",\"description\":\"Business Insider is a fast-growing business site with deep financial, media, tech, and other industry verticals. Launched in 2007, the site is now the largest business news site on the web.\",\"url\":\"http://www.businessinsider.com\",\"category\":\"business\",\"language\":\"en\",\"country\":\"us\"},{\"id\":\"business-insider-uk\",\"name\":\"Business Insider (UK)\",\"description\":\"Business Insider is a fast-growing business site with deep financial, media, tech, and other industry verticals. Launched in 2007, the site is now the largest business news site on the web.\",\"url\":\"http://uk.businessinsider.com\",\"category\":\"business\",\"language\":\"en\",\"country\":\"gb\"},{\"id\":\"die-zeit\",\"name\":\"Die Zeit\",\"description\":\"Aktuelle Nachrichten, Kommentare, Analysen und Hintergrundberichte aus Politik, Wirtschaft, Gesellschaft, Wissen, Kultur und Sport lesen Sie auf ZEIT ONLINE.\",\"url\":\"http://www.zeit.de/index\",\"category\":\"business\",\"language\":\"de\",\"country\":\"de\"},{\"id\":\"financial-post\",\"name\":\"Financial Post\",\"description\":\"Find the latest happenings in the Canadian Financial Sector and stay up to date with changing trends in Business Markets. Read trading and investing advice from professionals.\",\"url\":\"http://business.financialpost.com\",\"category\":\"business\",\"language\":\"en\",\"country\":\"ca\"},{\"id\":\"fortune\",\"name\":\"Fortune\",\"description\":\"Fortune 500 Daily and Breaking Business News\",\"url\":\"http://fortune.com\",\"category\":\"business\",\"language\":\"en\",\"country\":\"us\"},{\"id\":\"handelsblatt\",\"name\":\"Handelsblatt\",\"description\":\"Auf Handelsblatt lesen sie Nachrichten über Unternehmen, Finanzen, Politik und Technik. Verwalten Sie Ihre Finanzanlagen mit Hilfe unserer Börsenkurse.\",\"url\":\"http://www.handelsblatt.com\",\"category\":\"business\",\"language\":\"de\",\"country\":\"de\"},{\"id\":\"il-sole-24-ore\",\"name\":\"Il Sole 24 Ore\",\"description\":\"Notizie di economia, cronaca italiana ed estera, quotazioni borsa in tempo reale e di finanza, norme e tributi, fondi e obbligazioni, mutui, prestiti e lavoro a cura de Il Sole 24 Ore.\",\"url\":\"https://www.ilsole24ore.com\",\"category\":\"business\",\"language\":\"it\",\"country\":\"it\"},{\"id\":\"info-money\",\"name\":\"InfoMoney\",\"description\":\"No InfoMoney você encontra tudo o que precisa sobre dinheiro. Ações, investimentos, bolsas de valores e muito mais. Aqui você encontra informação que vale dinheiro!\",\"url\":\"https://www.infomoney.com.br\",\"category\":\"business\",\"language\":\"pt\",\"country\":\"br\"},{\"id\":\"les-echos\",\"name\":\"Les Echos\",\"description\":\"Toute l'actualité économique, financière et boursière française et internationale sur Les Echos.fr\",\"url\":\"https://www.lesechos.fr\",\"category\":\"business\",\"language\":\"fr\",\"country\":\"fr\"},{\"id\":\"the-wall-street-journal\",\"name\":\"The Wall Street Journal\",\"description\":\"WSJ online coverage of breaking news and current headlines from the US and around the world. Top stories, photos, videos,ness\",\"language\":\"en\",\"country\":\"us\"},{\"id\":\"wirtschafts-woche\",\"name\":\"Wirtschafts Woche\",\"description\":\"Das Online-Portal des führenden Wirtschaftsmagazins in Deutschland. Das Entscheidende zu Unternehmen, Finanzen, Erfolg und Technik.\",\"url\":\"http://www.wiwo.de\",\"category\":\"business\",\"language\":\"de\",\"country\":\"de\"}]}"
        val body = Gson().fromJson(json, SourceResponse::class.java)
        return ApiSuccessResponse(body = body, "")
    }

    override suspend fun getArticles(
        q: String?,
        qInTitle: String?,
        sources: List<String>?,
        domains: List<String>?,
        excludeDomains: List<String>?,
        from: String?,
        to: String?,
        language: String?,
        page: Int,
        pageSize: Int
    ): ApiResponse<ArticleResponse> {
        val json = "{\"status\":\"ok\",\"totalResults\":2,\"articles\":[{\"source\":{\"id\":\"australian-financial-review\",\"name\":\"Australian Financial Review\"},\"author\":\"Mark Eggleton\",\"title\":\"Businesses must bank on secure future\",\"description\":\"According to the report, ransomware volume is running rampant and in Asia-Pacific alone, the number of attacks spiked by 455 per cent. And the attacks continue unabated, as highlighted at the G7 summit last weekend. There have been several significant recent …\",\"url\":\"https://www.afr.com/companies/financial-services/businesses-must-bank-on-secure-future-20210615-p5811j\",\"urlToImage\":\"https://static.ffx.io/images/s_crop_custom/e_sharpen:25%2Cq_85%2Cf_jpg/t_afr_no_label_no_age_social_wm/40c67467a7a095062a8807ae3857ec2663d80b01\",\"publishedAt\":\"2021-06-18T13:32:00Z\",\"content\":\"Bearing this in mind, in their final summit communiqué, G7 leaders called on all states to urgently identify and disrupt ransomware criminal networks operating from within their borders, and hold tho… [+7963 chars]\"},{\"source\":{\"id\":\"australian-financial-review\",\"name\":\"Australian Financial Review\"},\"author\":\"Paul Smith\",\"title\":\"Ex McKinsey, Goldman alum raise \$3m for fintech start-up Driva\",\"description\":\"A VC fund and leading entrepreneurs have backed an online car-financing marketplace that claims to get users a clear and good value quote within minutes.\",\"url\":\"https://www.afr.com/technology/ex-mckinsey-goldman-alum-raise-3m-for-fintech-start-up-20210521-p57tv4\",\"urlToImage\":\"https://static.ffx.io/images/asa5cf58d6e95ce5d448ae111275060810c013af\",\"publishedAt\":\"2021-05-31T00:34:58Z\",\"content\":\"This sounds pretty straightforward, but on the back end, its a whole series of complex lender credit policies and pricing criteria that weve had to price in, Mr Montarello said.\\r\\nThe company launched… [+3733 chars]\"}]}"
        val body = Gson().fromJson(json, ArticleResponse::class.java)
        return ApiSuccessResponse(body = body, "")
    }

    private fun generateCommaSeparatedString(list: List<String>?): String? {
        if (list.isNullOrEmpty()) return null
        val sb = StringBuilder()
        for (str in list) {
            sb.append("$str,")
        }
        return sb.removeSuffix(",").toString()
    }
}
