package com.example.android.popluarmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueryUtils {
    private static final String TEST_RESPONSE = "{\"kind\":\"books#volumes\",\"totalItems\":1063," +
            "\"items\":[{\"kind\":\"books#volume\",\"id\":\"NXmSmcmTfH8C\",\"etag\":\"9BP3oPwVDPg\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/NXmSmcmTfH8C\",\"volumeInfo\":{\"title\":\"Blah Blah Blah\",\"subtitle\":\"What To Do When Words Don't Work\",\"authors\":[\"Dan Roam\"],\"publisher\":\"Penguin\",\"publishedDate\":\"2011-11-01\",\"description\":\"Ever been to so many meetings that you couldn't get your work done? Ever fallen asleep during a bulletpoint presentation? Ever watched the news and ended up knowing less? Welcome to the land of Blah Blah Blah. The Problem: We talk so much that we don't think very well. Powerful as words are, we fool ourselves when we think our words alone can detect, describe, and defuse the multifaceted problems of today. They can't-and that's bad, because words have become our default thinking tool. The Solution: This book offers a way out of blah-blah-blah. It's called \\\"Vivid Thinking.\\\" In Dan Roam's first acclaimed book, The Back of the Napkin, he taught readers how to solve problems and sell ideas by drawing simple pictures. Now he proves that Vivid Thinking is even more powerful. This technique combines our verbal and visual minds so that we can think and learn more quickly, teach and inspire our colleagues, and enjoy and share ideas in a whole new way. The Destination: No more blah-blah-blah. Through Vivid Thinking, we can make the most complicated subjects suddenly crystal clear. Whether trying to understand a Harvard Business School class, or what went down in the Conan versus Leno battle for late-night TV, or what Einstein thought about relativity, Vivid Thinking provides a way to clarify anything. Through dozens of guided examples, Roam proves that anyone can apply this systematic approach, from leftbrain types who hate to draw to right-brainers who hate to write. This isn't just a book about improving communications, presentations, and ideation; it's about removing the blah-blah- blah from your life for good.\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9781101558706\"},{\"type\":\"ISBN_10\",\"identifier\":\"1101558709\"}],\"readingModes\":{\"text\":true,\"image\":false},\"pageCount\":368,\"printType\":\"BOOK\",\"categories\":[\"Business & Economics\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"0.5.4.0.preview.2\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=NXmSmcmTfH8C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=NXmSmcmTfH8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=NXmSmcmTfH8C&printsec=frontcover&dq=blah&hl=&cd=1&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=NXmSmcmTfH8C&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/Blah_Blah_Blah.html?hl=&id=NXmSmcmTfH8C\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":14.99,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amount\":14.99,\"currencyCode\":\"USD\"},\"buyLink\":\"http://books.google.com/books?id=NXmSmcmTfH8C&dq=blah&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":1.499E7,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amountInMicros\":1.499E7,\"currencyCode\":\"USD\"},\"giftable\":true}]},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED_FOR_ACCESSIBILITY\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Blah_Blah_Blah-sample-epub.acsm?id=NXmSmcmTfH8C&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com/books/reader?id=NXmSmcmTfH8C&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"The Solution: This book offers a way out of blah-blah-blah. It&#39;s called &quot;Vivid Thinking.&quot; In Dan Roam&#39;s first acclaimed book, The Back of the Napkin, he taught readers how to solve problems and sell ideas by drawing simple pictures.\"}}," +
            "{\"kind\":\"books#volume\",\"id\":\"eWen6nfzpQsC\",\"etag\":\"6Vt3f4nFI4o\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/eWen6nfzpQsC\",\"volumeInfo\":{\"title\":\"Happy Birthday . . . blah, blah, blah\",\"authors\":[\"Mikwright, Ltd.\"],\"publisher\":\"Andrews McMeel Publishing\",\"publishedDate\":\"2012-11-06\",\"description\":\"Laugh! Go ahead and laugh--out loud! It could be the hair, it could be the clothes, or it might be the fact that your family and friends closely resemble these. When Tim Mikkelsen and Phyllis Wright-Herman started creating goofy greeting cards, they thought, \\\"Let's just say what people want to say, but won't. Let's provoke, nudge, and push our skewed sense of humor to the masses and see what happens.\\\" To their wildest amazement, \\\"the big goof\\\" caught on--and now it's on fire. Happy Birthday...Blah, Blah, Blah is a collection of the best of the best on birthdays. Sometimes you love 'em and sometimes, well, there could be worse things--you could be dead. Remember, it's important to laugh, but it's imperative to laugh at ourselves--everyone else is. Enjoy!\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9780740788123\"},{\"type\":\"ISBN_10\",\"identifier\":\"0740788124\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":80,\"printType\":\"BOOK\",\"categories\":[\"Humor\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"1.3.3.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=eWen6nfzpQsC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=eWen6nfzpQsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=eWen6nfzpQsC&printsec=frontcover&dq=blah&hl=&cd=2&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=eWen6nfzpQsC&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/Happy_Birthday_blah_blah_blah.html?hl=&id=eWen6nfzpQsC\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":2.99,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amount\":2.51,\"currencyCode\":\"USD\"},\"buyLink\":\"http://books.google.com/books?id=eWen6nfzpQsC&dq=blah&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":2990000.0,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amountInMicros\":2510000.0,\"currencyCode\":\"USD\"},\"giftable\":true}]},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Happy_Birthday_blah_blah_blah-sample-epub.acsm?id=eWen6nfzpQsC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Happy_Birthday_blah_blah_blah-sample-pdf.acsm?id=eWen6nfzpQsC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://books.google.com/books/reader?id=eWen6nfzpQsC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Laugh! Go ahead and laugh--out loud!\"}}," +
            "{\"kind\":\"books#volume\",\"id\":\"LiBOm1XMRO8C\",\"etag\":\"vTokq2OfuSE\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/LiBOm1XMRO8C\",\"volumeInfo\":{\"title\":\"From Blah to Awe\",\"subtitle\":\"Shaking Up a Boring Faith\",\"authors\":[\"Jenna Lucado Bishop\"],\"publisher\":\"Thomas Nelson Inc\",\"publishedDate\":\"2012\",\"description\":\"Everyone, especially teenagers, struggles with being bored with God from time to time. Sometimes church services and Bible reading don't seem that exciting, and it's easy to get busy and not make time to pray . . . but when this happens, we are missing out. Bishop shares her testimony and others' stories to see what a radical, living relationship with God looks like.\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9781400316557\"},{\"type\":\"ISBN_10\",\"identifier\":\"1400316553\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":227,\"printType\":\"BOOK\",\"categories\":[\"Juvenile Nonfiction\"],\"averageRating\":4.0,\"ratingsCount\":15,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=LiBOm1XMRO8C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=LiBOm1XMRO8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=LiBOm1XMRO8C&printsec=frontcover&dq=blah&hl=&cd=3&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=LiBOm1XMRO8C&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/From_Blah_to_Awe.html?hl=&id=LiBOm1XMRO8C\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED_FOR_ACCESSIBILITY\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com/books/reader?id=LiBOm1XMRO8C&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Bishop shares her testimony and others&#39; stories to see what a radical, living relationship with God looks like.\"}}," +
            "{\"kind\":\"books#volume\",\"id\":\"XneJtQAACAAJ\",\"etag\":\"A0amf9iCEk8\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/XneJtQAACAAJ\",\"volumeInfo\":{\"title\":\"Blah, Blah, Blah\",\"subtitle\":\"What to Do when Words Don't Work\",\"authors\":[\"Dan Roam\"],\"publisher\":\"Marshall Cavendish International (Asia) Pte Limited\",\"publishedDate\":\"2012\",\"description\":\"The vivid combination of words and pictures can help readers think and learn more quickly, teach and inspire their colleagues, and enjoy happier lives in general. Roam's solution is to merge the best aspects of one's visual and verbal minds.\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"9814382051\"},{\"type\":\"ISBN_13\",\"identifier\":\"9789814382052\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":346,\"printType\":\"BOOK\",\"categories\":[\"Communication\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=XneJtQAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=XneJtQAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=XneJtQAACAAJ&dq=blah&hl=&cd=4&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=XneJtQAACAAJ&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/Blah_Blah_Blah.html?hl=&id=XneJtQAACAAJ\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com/books/reader?id=XneJtQAACAAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Roam&#39;s solution is to merge the best aspects of one&#39;s visual and verbal minds.\"}}," +
            "{\"kind\":\"books#volume\",\"id\":\"JS-JAAAAQBAJ\",\"etag\":\"PWY0tWDOXPg\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/JS-JAAAAQBAJ\",\"volumeInfo\":{\"title\":\"Blah, Blah, Blah: What to do When Words Don’t Work\",\"subtitle\":\"\",\"authors\":[\"Dan Roam\"],\"publisher\":\"Marshall Cavendish International Asia Pte Ltd\",\"publishedDate\":\"2011-12-15\",\"description\":\"Ever been to so many meetings that you couldn’t get your work done? Ever fallen asleep during a bullet-point presentation? Ever watched the news and ended up knowing less? Welcome to the land of Blah, Blah, Blah, in which talk and words prevent us from thinking. As powerful as words are, we fool ourselves when we think our words alone can detect, describe and defuse the multifaceted problems of today. This book offers a way out of Blah, Blah, Blah. It’s called “Vivid Thinking”, which combines our verbal and visual minds so that we can think and learn more quickly, teach and inspire our colleagues, and enjoy and share ideas in a new and more effective way. Through Vivid Thinking, we can make the most complicated subjects suddenly crystal clear – something which is proving increasingly valuable in this complex world of ours\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9789814484855\"},{\"type\":\"ISBN_10\",\"identifier\":\"9814484857\"}],\"readingModes\":{\"text\":true,\"image\":false},\"pageCount\":362,\"printType\":\"BOOK\",\"categories\":[\"Business & Economics\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"1.3.3.0.preview.2\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=JS-JAAAAQBAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=JS-JAAAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=JS-JAAAAQBAJ&dq=blah&hl=&cd=5&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=JS-JAAAAQBAJ&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/Blah_Blah_Blah_What_to_do_When_Words_Don.html?hl=&id=JS-JAAAAQBAJ\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true},\"pdf\":{\"isAvailable\":true},\"webReaderLink\":\"http://books.google.com/books/reader?id=JS-JAAAAQBAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"As powerful as words are, we fool ourselves when we think our words alone can detect, describe and defuse the multifaceted problems of today. This book offers a way out of Blah, Blah, Blah.\"}}," +
            "{\"kind\":\"books#volume\",\"id\":\"sZeYjgYpm3QC\",\"etag\":\"u64oNv7S+QA\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/sZeYjgYpm3QC\",\"volumeInfo\":{\"title\":\"My Mum Says \\\"Blah Blah Blah\\\"\",\"authors\":[\"Aly Walsh\"],\"publisher\":\"Strategic Book Publishing & Rights Agency\",\"publishedDate\":\"2012-02-01\",\"description\":\"Kids say the darndest things! Author Aly Walsh got the idea for this book when her four-year-old son told her, Mum, all you say is blah blah blah. My Mum Says Blah Blah Blah is the author's light-hearted and fun story of a typical day raising a little boy or girl in today's world, where parents are often tuned out by their kids who find much more interesting things to listen to than our Blah Blah Blah. If you've ever felt that what you're telling your children is falling on deaf ears, this is the book for you and your child to read together. You'll not only get vindication that you are not alone, but you'll also get some chuckles along the way. Children aged three to seven will especially enjoy this cleverly illustrated book.\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9781612048543\"},{\"type\":\"ISBN_10\",\"identifier\":\"1612048544\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":26,\"printType\":\"BOOK\",\"categories\":[\"Juvenile Nonfiction\"],\"averageRating\":1.0,\"ratingsCount\":1,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=sZeYjgYpm3QC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=sZeYjgYpm3QC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=sZeYjgYpm3QC&printsec=frontcover&dq=blah&hl=&cd=6&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=sZeYjgYpm3QC&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/My_Mum_Says_Blah_Blah_Blah.html?hl=&id=sZeYjgYpm3QC\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com/books/reader?id=sZeYjgYpm3QC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Kids say the darndest things! Author Aly Walsh got the idea for this book when her four-year-old son told her, Mum, all you say is blah blah blah.\"}}," +
            "{\"kind\":\"books#volume\",\"id\":\"4ZP4AQAAQBAJ\",\"etag\":\"PxKtQLNsN/g\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/4ZP4AQAAQBAJ\",\"volumeInfo\":{\"title\":\"Martha Blah Blah\",\"publisher\":\"Houghton Mifflin Harcourt\",\"publishedDate\":\"2013-11-05\",\"description\":\"Something's wrong with Martha, the talking dog! She has eaten her daily bowl of alphabet soup, but when she opens her mouth to speak, strange sounds come out instead of words. Fortunately her nose still works, and she follows it to the source of the mystery.\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9780547530598\"},{\"type\":\"ISBN_10\",\"identifier\":\"0547530595\"}],\"readingModes\":{\"text\":false,\"image\":true},\"pageCount\":32,\"printType\":\"BOOK\",\"categories\":[\"Juvenile Fiction\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"preview-1.0.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=4ZP4AQAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=4ZP4AQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=4ZP4AQAAQBAJ&pg=PT2&dq=blah&hl=&cd=7&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=4ZP4AQAAQBAJ&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/Martha_Blah_Blah.html?hl=&id=4ZP4AQAAQBAJ\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":4.99,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amount\":4.99,\"currencyCode\":\"USD\"},\"buyLink\":\"http://books.google.com/books?id=4ZP4AQAAQBAJ&dq=blah&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":4990000.0,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amountInMicros\":4990000.0,\"currencyCode\":\"USD\"},\"giftable\":true}]},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Martha_Blah_Blah-sample-pdf.acsm?id=4ZP4AQAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://books.google.com/books/reader?id=4ZP4AQAAQBAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"_xExAAAAIAAJ\",\"etag\":\"EuGATYp/oqs\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/_xExAAAAIAAJ\",\"volumeInfo\":{\"title\":\"Hiss! Boom!! Blah!!!\",\"subtitle\":\"A three act American comedy in fifty scenes\",\"authors\":[\"George Middleton\"],\"publishedDate\":\"1933\",\"industryIdentifiers\":[{\"type\":\"OTHER\",\"identifier\":\"UCAL:B3711126\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":192,\"printType\":\"BOOK\",\"categories\":[\"Drama\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.1.0.0.preview.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=_xExAAAAIAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=_xExAAAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=_xExAAAAIAAJ&q=blah&dq=blah&hl=&cd=8&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=_xExAAAAIAAJ&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/Hiss_Boom_Blah.html?hl=&id=_xExAAAAIAAJ\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com/books/reader?id=_xExAAAAIAAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"-US3Rn3RY3MC\",\"etag\":\"SZUgwMowHtM\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/-US3Rn3RY3MC\",\"volumeInfo\":{\"title\":\"Zeke Meeks vs the Big Blah-rific Birthday\",\"authors\":[\"D.L. Green\"],\"publisher\":\"Capstone\",\"publishedDate\":\"2013\",\"description\":\"When Zeke's birthday party falls near Grace Chang's and Owen Leech's big bashes, he decides to cancel his party. After all, even he would rather go to their over-the-top parties than his simple celebration. But Zeke and his classmates are in for a surprise. Bigger doesn't always mean better.\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"1479522902\"},{\"type\":\"ISBN_13\",\"identifier\":\"9781479522903\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":128,\"printType\":\"BOOK\",\"categories\":[\"Juvenile Fiction\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"0.1.1.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=-US3Rn3RY3MC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=-US3Rn3RY3MC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=-US3Rn3RY3MC&printsec=frontcover&dq=blah&hl=&cd=9&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=-US3Rn3RY3MC&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/Zeke_Meeks_vs_the_Big_Blah_rific_Birthda.html?hl=&id=-US3Rn3RY3MC\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":5.95,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amount\":4.58,\"currencyCode\":\"USD\"},\"buyLink\":\"http://books.google.com/books?id=-US3Rn3RY3MC&dq=blah&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":5950000.0,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amountInMicros\":4580000.0,\"currencyCode\":\"USD\"},\"giftable\":true}]},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Zeke_Meeks_vs_the_Big_Blah_rific_Birthda-sample-epub.acsm?id=-US3Rn3RY3MC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Zeke_Meeks_vs_the_Big_Blah_rific_Birthda-sample-pdf.acsm?id=-US3Rn3RY3MC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://books.google.com/books/reader?id=-US3Rn3RY3MC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"When Zeke&#39;s birthday party falls near Grace Chang&#39;s and Owen Leech&#39;s big bashes, he decides to cancel his party.\"}}," +
            "{\"kind\":\"books#volume\",\"id\":\"-NxKCAAAQBAJ\",\"etag\":\"HMDVvIjhSzw\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/-NxKCAAAQBAJ\",\"volumeInfo\":{\"title\":\"Blah Blah\",\"authors\":[\"Gerry Cryer\"],\"publisher\":\"Lulu Press, Inc\",\"publishedDate\":\"2014-07-11\",\"description\":\"To the outsider, Tommy leads a very, very successful life. Which it is, but it’s also dull. And lonely. He has much to say, but no-one listens. He has strong opinions, but no-one cares. He has vibrant dreams, but no-one shares. A sense of humour, but no-one laughs. He wants to be a hero, but he has no-one to save. Instead, he immerses himself in a private world of desire, fantasy and adventure in an attempt to escape from his flat-lining life. Then he meets Scunt. She is young, feisty and deeply enthralling ... and on the run from a violent pimp and the police. She needs help – and Tommy is there. Finally a chance to become a hero of his own life. And maybe hers. “A clever, funny and insightful romp through the mind of a world-weary, world-wary man who doesn’t really care about ‘political correctness’. Prepare to be offended!” “A 21st century man who just wants to be anyone but who he is, and do anything but what he does.”\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9780992721350\"},{\"type\":\"ISBN_10\",\"identifier\":\"0992721350\"}],\"readingModes\":{\"text\":true,\"image\":true},\"printType\":\"BOOK\",\"categories\":[\"Fiction\"],\"maturityRating\":\"MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.1.1.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=-NxKCAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=-NxKCAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com/books?id=-NxKCAAAQBAJ&pg=PT1&dq=blah&hl=&cd=10&source=gbs_api\",\"infoLink\":\"http://books.google.com/books?id=-NxKCAAAQBAJ&dq=blah&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com/books/about/Blah_Blah.html?hl=&id=-NxKCAAAQBAJ\"},\"saleInfo\":{\"country\":\"US\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":4.99,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amount\":3.82,\"currencyCode\":\"USD\"},\"buyLink\":\"http://books.google.com/books?id=-NxKCAAAQBAJ&dq=blah&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":4990000.0,\"currencyCode\":\"USD\"},\"retailPrice\":{\"amountInMicros\":3820000.0,\"currencyCode\":\"USD\"},\"giftable\":true}]},\"accessInfo\":{\"country\":\"US\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Blah_Blah-sample-epub.acsm?id=-NxKCAAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Blah_Blah-sample-pdf.acsm?id=-NxKCAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://books.google.com/books/reader?id=-NxKCAAAQBAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"\\u003cb\\u003eBlah Blah\\u003c/b\\u003e Epub version Copyright © Gerry Cryer 2014 Published by Brovary \\u003cbr\\u003e\\nLimited All rights reserved. ISBN 9780992721350 The moral right of Gerry Cryer \\u003cbr\\u003e\\nto be identified as the author of this work has been asserted in accordance with \\u003cbr\\u003e\\nthe&nbsp;...\"}}]}";

    private QueryUtils() {}

    public static ArrayList<movie> extractDetails(String json, boolean list) {
        ArrayList<movie> movies = new ArrayList<>();
        try {
            if (list) {
                JSONObject root = new JSONObject(json);
                JSONArray items = root.getJSONArray("results");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject currentMovie = items.getJSONObject(i);
                    String posterPath = currentMovie.getString("poster_path");
                    long movieID = currentMovie.getLong("id");
                    movies.add(new movie(movieID, posterPath));
                }
            } else {
                JSONObject currentMovie = new JSONObject(json);
                String posterPath = currentMovie.getString("poster_path");
                long movieID = currentMovie.getLong("id");
                movies.add(new movie(movieID, posterPath));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
